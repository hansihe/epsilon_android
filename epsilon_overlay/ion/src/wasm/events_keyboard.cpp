#include <ion/events.h>
#include "events_keyboard.h"
#include "display.h"
extern "C" {
//#include <SDL/SDL.h>
}
#include <emscripten.h>

template<typename T, int N>
class Queue {
public:
  Queue() : m_first(&m_elements[0]), m_last(&m_elements[0]) {}
  int size() {
    if (m_last >= m_first) {
      return m_last - m_first;
    } else {
      return m_last - (m_first - N);
    }
  }

  void enqueue(T element) {
    if (size() > N) {
      // Queue is full
      return;
    }
    *m_last = element;
    m_last = next(m_last);
  }

  T dequeue() {
    if (size() <= 0) {
      // Dequeueing an empty queue
      return T();
    }
    T e = *m_first;
    m_first = next(m_first);
    return e;
  }

private:
  T * next(T * p) {
    if (p >= m_elements + N) {
      return m_elements;
    } else {
      return p + 1;
    }
  }
  T * m_first;
  T * m_last;
  T m_elements[N];
};

static Queue<Ion::Events::Event, 1024> sEventQueue;

Ion::Keyboard::State sKeyboardState;

void IonEventsEmscriptenKeyDown(int keyNumber) {
  Ion::Keyboard::Key key = static_cast<Ion::Keyboard::Key>(keyNumber);
  sKeyboardState.setKey(key);
  /* Note: This uses the *current* modifier state to generate the event. If some
   * other modifiers were in the queue before, those won't be taken into account
   * when the event corresponding to this key is dequeued.
   * In practice, this should not happen because we push keys one by one. */
  Ion::Events::Event event = Ion::Events::Event(key, Ion::Events::isShiftActive(), Ion::Events::isAlphaActive());
  sEventQueue.enqueue(event);
}

void IonEventsEmscriptenKeyUp(int keyNumber) {
  Ion::Keyboard::Key key = static_cast<Ion::Keyboard::Key>(keyNumber);
  sKeyboardState.clearKey(key);
}

void IonEventsEmscriptenPushEvent(int eventNumber) {
  sEventQueue.enqueue(Ion::Events::Event(eventNumber));
}

Ion::Keyboard::State Ion::Keyboard::scan() {
  /* The following call to emscripten_sleep gives the JS VM a chance to do a run
   * loop iteration. This in turns gives the browser an opportunity to call the
   * IonEventsEmscriptenPushKey function, therefore modifying the sKeyboardState
   * global variable before it is returned by this Ion::Keyboard::scan.
   * On Emterpreter-async, emscripten_sleep is actually a wrapper around the JS
   * function setTimeout, which can be called with a value of zero. Doing so
   * puts the callback at the end of the queue of callbacks to be processed. */
  emscripten_sleep(0);

  /* Grab this opporunity to refresh the display. In practice, this routine is
   * called from micropython_port_vm_hook_loop once in a while, so this gives us
   * an opportunity to refresh the display during the execution of a
   * long-running Python script. */
  Ion::Display::Emscripten::refresh();

  return sKeyboardState;
}

namespace Ion {
namespace Events {

static bool sleepWithTimeout(int duration, int * timeout) {
  if (*timeout >= duration) {
    emscripten_sleep(duration);
    *timeout -= duration;
    return false;
  } else {
    emscripten_sleep(*timeout);
    *timeout = 0;
    return true;
  }
}

//ShiftAlphaStatus shiftAlphaStatus() {
//  return ShiftAlphaStatus::Default;
//}
//bool isShiftActive() {
//    return false;
//}
//bool isAlphaActive() {
//    return false;
//}
//void setShiftAlphaStatus(ShiftAlphaStatus s) {
//}

Event getEvent(int * timeout) {
  // If multiple events are in the queue, don't waste time refreshing the display
  if (sEventQueue.size() <= 1) {
    Ion::Display::Emscripten::refresh();
  }

  while (true) {
    // Look up events in the queue
    if (sEventQueue.size() > 0) {
      Event event = sEventQueue.dequeue();
      if (event.isKeyboardEvent()) {
          updateModifiersFromEvent(event);
      }
      return event;
    }

    // Or directly from browser events, converted to SDL events by Emscripten
    //SDL_Event sdlEvent;
    //SDL_PollEvent(&sdlEvent);
    //Event eventFromSDL = eventFromSDLEvent(sdlEvent);
    //if (eventFromSDL != None) {
    //  return eventFromSDL;
    //}

    if (sleepWithTimeout(10, timeout)) {
      return None;
    }
  }
  return None;
}

}
}

namespace Ion {
namespace Events {
namespace Emscripten {

void init() {
  //SDL_EnableUNICODE(1); // We're using Unicode values from Keyboard input
}

}
}
}

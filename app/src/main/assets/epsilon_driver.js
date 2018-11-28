var mainCanvas = document.querySelector(".calculator .screen");
var Module = {
    arguments: ["--language", "en-us"],
    canvas: mainCanvas,
    onDisplayRefresh: function() {},
};
Epsilon(Module);

function dispatchEpsilonEvent(eventNum) {
    Module._IonEventsEmscriptenPushEvent(eventNum);
}
function dispatchEpsilonKeyUp(keyNum) {
    Module._IonEventsEmscriptenKeyUp(keyNum);
}
function dispatchEpsilonKeyDown(keyNum) {
    Module._IonEventsEmscriptenKeyDown(keyNum);
}
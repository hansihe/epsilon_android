#!/bin/bash
set -e

if [ ! -d "epsilon" ]; then
    git clone https://github.com/numworks/epsilon.git
fi

cd epsilon
git checkout 3b81f9bedca145f44376fa259c7c082d3847c00b

cp -r ../epsilon_overlay/* .

PLATFORM=wasm make
cd ..

cp epsilon/epsilon.js app/src/main/assets/
cp epsilon/epsilon.js.mem app/src/main/assets/

const mix = require('laravel-mix');
const path = require('path');
const devPath = path.resolve(__dirname, 'resources');
const scssPath = path.resolve(devPath, 'scss');
const jsPath = path.resolve(devPath, 'js');
const out = path.resolve(__dirname, 'src/main/resources/public');
const cssOut = path.resolve(out, 'css');
const jsOut = path.resolve(out, 'js');

mix.js(path.resolve(jsPath, 'main.js'), jsOut);
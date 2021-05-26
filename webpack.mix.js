const mix = require('laravel-mix');
const path = require('path');
const devPath = path.resolve(__dirname, 'resources');
const scssPath = path.resolve(devPath, 'scss');
const jsPath = path.resolve(devPath, 'js');
const out = path.resolve(__dirname, 'src/main/resources/public');
const cssOut = path.resolve(out, 'css');
const jsOut = path.resolve(out, 'js');

//mix.js(path.resolve(jsPath, 'main.js'), jsOut);const mix = require('laravel-mix');
mix.webpackConfig({module: {rules: [{ test: /\.scss$/, loader: "sass-loader", options: {additionalData: '@import "resources/scss/_variables.scss";'}}]}}).options({hmrOptions:{port:8001}}).js(path.resolve(jsPath, 'main.js'), jsOut).vue({version:2});
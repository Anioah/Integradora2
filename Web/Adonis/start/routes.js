'use strict'

/*
|--------------------------------------------------------------------------
| Routes
|--------------------------------------------------------------------------
|
| Http routes are entry points to your web application. You can create
| routes for different URLs and bind Controller actions to them.
|
| A complete guide on routing is available here.
| http://adonisjs.com/docs/4.1/routing
|
*/

/** @type {typeof import('@adonisjs/framework/src/Route/Manager')} */
const Route = use('Route')

Route.get('/', () => {
  return { greeting: 'Hello world in JSON' }
})

// User login and register
Route.post('register','UserController.register');
Route.post('login','UserController.login');

//User validation for HTTP request
Route.post('userVerification','UserController.tokenValidation');

Route.group(() => {

  // Lectures routes
  Route.post('newLecture','LecturaController.store');  //Este es para Zapata
  Route.get('show','LecturaController.show');//Zamora
  Route.post('perTemperature','LecturaController.showPerTemperatures');//Zamora
  Route.post('perHumedity','LecturaController.showPerHumedity');//Zamora
  Route.post('perPresion','LecturaController.showPerPresion');//Zamora

}).middleware(['auth']);

//testing
Route.post('test','LecturaController.getData');

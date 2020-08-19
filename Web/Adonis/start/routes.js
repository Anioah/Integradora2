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
  Route.post('newLecture','LecturaController.store');
  Route.get('show','LecturaController.show');
  Route.put('updateData','LecturaController.update');
  Route.post('perTemperature','LecturaController.showPerTemperatures');
  Route.post('perHumedity','LecturaController.showPerHumedity');
  Route.post('perPresion','LecturaController.showPerPresion');
  Route.post('perDate','LecturaController.showPerDate')
  Route.post('betweenDates','LecturaController.showBetweenDates');
  Route.delete('deleteData','LecturaController.delete');

}).middleware(['auth']);

//testing
Route.get('test','LecturaController.getDate');
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
  Route.post('newLecture','LecturaController.store'); // store new lectures
  Route.get('show','LecturaController.show'); // show all lectures
  Route.put('updateData','LecturaController.update'); // update an existent lecture
  Route.post('perTemperature','LecturaController.showPerTemperatures'); // show lectures per temperature value
  Route.post('perHumedity','LecturaController.showPerHumedity'); // show lectures per humedity value
  Route.post('perPresion','LecturaController.showPerPresion'); // show lectures per presion value
  Route.post('perDate','LecturaController.showPerDate') // show lecture per day
  Route.post('betweenDates','LecturaController.showBetweenDates'); // show lectures between 2 days
  Route.delete('deleteData','LecturaController.delete');  // delete an existent lecture

  // average 
  Route.get('calculateAverage', 'LecturaController.makePromedio'); // store a new average
  
  //get last average
  Route.get('average','LecturaController.getAverage');

}).middleware(['auth']);

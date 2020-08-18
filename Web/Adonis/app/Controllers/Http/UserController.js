'use strict'

const User = use('App/Models/User');

class UserController {

  // register user
  async register({request, response}) {

    const data = await request.all();

    let user = new User();

    user.username = data.username;
    user.email = data.email;
    user.password = data.password;

    // error/exception handling pending
    if (user.save()) {
      return response.status(200).json({
        status: 'OK',
        message: 'El usuario ha sido registrado exitosamente.'
      })
    }

    // here goes the type of error
    return response.status(500).json(
      {
        status: 'FAILED',
        // hardcoded error message
        message: 'Hubo un error al tratar de registrar al usuario. Intente más tarde.'
      }
    )

  } // async register() end

  async login({request, auth}){

    const { email , password } = await request.all();

    return auth.withRefreshToken().attempt(email, password);

  } // async login() end

  async update({request, response}){

    const data = await request.all();

    let user = await User.find(data.id);

    // unchaged user properties previous update
    const _user = {
      id: user.id,
      username: user.username,
      email: user.email,
      created_at: user.created_at,
      updated_at: user.updated_at
    }

    // assigning the new values of the user properties to update
    user.username = data.username;
    user.email = data.email;

    // try to update the user
    if(user.save())
    {
      return response.status(200).json(
        {
          before:_user,
          after:user
        }
      );
    }

  } // async update() end

  /*----------just for testing----------*/
  async get()
  {
    return User.all();
  }
  /*----------just for testing----------*/

  // delete the user
  async delete()
  {

  }

  async tokenValidation({response, auth}) {
    try {
      const user = await auth.getUser()
      return response.status(200).json(true)
    } catch (error) {
      response.json(false)
    }
  }

}

module.exports = UserController

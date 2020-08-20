'use strict'

/** @type {typeof import('@adonisjs/lucid/src/Lucid/Model')} */
const Model = use('Model')

class Lectura extends Model {

    static mongoose = require('mongoose');

    static Schema = this.mongoose.Schema;

    static lecturasSchema =  new this.Schema({
    _id: this.Schema.Types.ObjectId,
    temperatura: this.Schema.Types.Number,
    humedad: this.Schema.Types.Number,
   // presion: this.Schema.Types.Number,
    latitud: this.Schema.Types.Number,
    longitud: this.Schema.Types.Number,
    fecha: this.Schema.Types.Date,
    identificador:  this.Schema.Types.Number
  });

  static lecturaMongo = this.mongoose.model('lecturas',this.lecturasSchema);

}

module.exports = Lectura

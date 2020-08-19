'use strict'

/** @type {import('@adonisjs/lucid/src/Schema')} */
const Schema = use('Schema')

class PromediosSchema extends Schema {
  up () {
    this.create('promedios', (table) => {
      table.increments()
      table.float('prom_temperatura')
      table.float('prom_humedad')
      table.float('prom_presion')
      table.timestamps()
    })
  }

  down () {
    this.drop('promedios')
  }
}

module.exports = PromediosSchema

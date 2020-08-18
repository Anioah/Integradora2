'use strict'

const Lectura = use('App/Models/Lectura');

class LecturaController {


    // get actual date
    getDate({request,response}){
        return response.status(200).json(super.dates.concat['dob'])
    }

    async mongoDBConnect(){

        await Lectura.mongoose.connect('mongodb://127.0.0.1:27017/proyecto_mzz', {useNewUrlParser: true, useMongoClient: true, useUnifiedTopology: true});
    }

    async closeConection(){
      await Lectura.mongoose.connection.close();
    }

    async index({response}){

        await this.mongoDBConnect();

        return await this.getData();

    }

    async store({request,response}){

      await this.closeConection();

      await this.mongoDBConnect();
  
      const data = await request.all();

      let lectura = await Lectura.lecturaMongo.create({
          _id : new Lectura.mongoose.Types.ObjectId(),
        temperatura : data.temperatura,
        humedad : data.humedad,
        presion : data.presion,
        latitud : data.latitud,
        longitud : data.longitud,
        fecha: data.fecha

      });

      try {
        return response.status(200).json({
            status:'OK',
            message:'Recopilación de datos guardada exitosamente',
            data:{
                lectura: lectura
            }
          });   
      } catch (error) {
          return response.status(400).json(error)
      }

    }

    async show({request,response}){

        await this.closeConection();

        await this.mongoDBConnect();

        const data = await request.all();

        const contratos = await this.getData(data.empleado_id);
    
        return response.status(200).json(contratos);
    

    }


    async update({request,response}){

        await this.closeConection();

        await this.mongoDBConnect();

        const data = await request.all();

        await Contrato.ContratosMongo.update({_id: Contrato.mongoose.Types.ObjectId(data._id)},{$set:{
            "empleado_id": data.empleado_id,
            "duracion_contrato" : data.duracion_contrato,
            "puesto" : data.puesto,
            "sueldo" : data.sueldo,
            "nombre_empresa" : data.nombre_empresa
            }
        })

        const newContrato = await Contrato.ContratosMongo.find({_id: Contrato.mongoose.Types.ObjectId(data._id)})

        return response.status(200).json({data: "Contrato actualizado correctamente" , "contrato" : newContrato })
    }

    async delete({request,response}){

        await this.closeConection();

        await this.mongoDBConnect();

        const data = await request.all();

        return await Contrato.ContratosMongo.findOneAndRemove({_id: Contrato.mongoose.Types.ObjectId(data._id)});;

    }


  async getData(empleado_id){

    if(empleado_id!==null){
      //return await ContratosMongo.find();
      return await Contrato.ContratosMongo.find({empleado_id: empleado_id});
    }
    return json("No data");

  }

}

module.exports = LecturaController

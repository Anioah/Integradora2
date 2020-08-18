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

      let getIdentificador = await Lectura.lecturaMongo.countDocuments();

      try {
        let lectura = await Lectura.lecturaMongo.create({
            _id : new Lectura.mongoose.Types.ObjectId(),
            temperatura : data.temperatura,
            humedad : data.humedad,
            presion : data.presion,
            latitud : data.latitud,
            longitud : data.longitud,
            fecha: data.fecha,
            identificador: getIdentificador
          });

        return response.status(200).json({
            status:'OK',
            message:'Recopilación de datos guardada exitosamente',
            data:{
                lectura: lectura
            }
          });   
      } catch (error) {
          return response.status(400).json({message: "Recopilación de datos no exitosa, intente nuevamente"})
      }

    }

    async show({request,response}){

        await this.closeConection();

        await this.mongoDBConnect();

        const data = await request.all();

        const lecturas = await Lectura.lecturaMongo.find().sort({"identificador":-1}).limit(10);
    
        return response.status(200).json(lecturas);

    }

    async showPerTemperatures({request,response}){

        await this.closeConection();

        await this.mongoDBConnect();

        const data = await request.all();

        const lecturas = await Lectura.lecturaMongo.find({"temperatura" : data.temperatura});

        if(lecturas == ""){
            return response.status(200).json({message: "No hay registros con ese parámetro de búsqueda"});
        }
        return response.status(200).json(lecturas);

    }

    async showPerHumedity({request,response}){

        await this.closeConection();

        await this.mongoDBConnect();

        const data = await request.all();

        const lecturas = await Lectura.lecturaMongo.find({"humedad" : data.humedad});

        if(lecturas == ""){
            return response.status(200).json({message: "No hay registros con ese parámetro de búsqueda"});
        }
        return response.status(200).json(lecturas);

    }

    async showPerPresion({request,response}){

        await this.closeConection();

        await this.mongoDBConnect();

        const data = await request.all();

        const lecturas = await Lectura.lecturaMongo.find({presion : data.presion});

        if(lecturas == ""){
            return response.status(200).json({message: "No hay registros con ese parámetro de búsqueda"});
        }
        return response.status(200).json(lecturas);
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


  async getData(_id){

    if(_id !== null){
        return await Lectura.lecturaMongo.find(Lectura.mongoose.Types.ObjectId(_id));
    }
    
    return await Lectura.lecturaMongo.find();

  }


}

module.exports = LecturaController

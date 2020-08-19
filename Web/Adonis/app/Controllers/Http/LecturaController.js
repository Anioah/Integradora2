'use strict'

const Lectura = use('App/Models/Lectura');

class LecturaController {


    // get actual date
    getDate({request,response}){
        return response.status(200).json(super.dates.concat['dob'])
    }

    // call conection
    async mongoDBConnect(){

        await Lectura.mongoose.connect('mongodb://127.0.0.1:27017/proyecto_mzz', {useNewUrlParser: true, useMongoClient: true, useUnifiedTopology: true});
    }

    // end conection
    async closeConection(){
      await Lectura.mongoose.connection.close();
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

        try {
            const lecturas = await Lectura.lecturaMongo.find();
            return response.status(200).json(lecturas);
        } catch (error) {
            return response.status(400).json({message: "No fue procesada la operación de manera satisfactoria, intente nuevamente"});
        }
        
    }

    async showPerTemperatures({request,response}){

        await this.closeConection();

        await this.mongoDBConnect();

        const data = await request.all();

        try {
            const lecturas = await Lectura.lecturaMongo.find({"temperatura" : data.temperatura});

            if(lecturas == ""){
                return response.status(200).json({message: "No hay registros con ese parámetro de búsqueda"});
            }
            return response.status(200).json(lecturas);
        } catch (error) {
            return response.status(400).json({message: "No fue procesada la operación de manera satisfactoria, intente nuevamente"});
        }

    }

    async showPerHumedity({request,response}){

        await this.closeConection();

        await this.mongoDBConnect();

        const data = await request.all();

        try {         
        const lecturas = await Lectura.lecturaMongo.find({"humedad" : data.humedad});

        if(lecturas == ""){
            return response.status(200).json({message: "No hay registros con ese parámetro de búsqueda"});
        }
        return response.status(200).json(lecturas);
   
        } catch (error) {
            return response.status(400).json({message: "No fue procesada la operación de manera satisfactoria, intente nuevamente"});
        }
    }

    async showPerPresion({request,response}){

        await this.closeConection();

        await this.mongoDBConnect();

        const data = await request.all();

        try {
            const lecturas = await Lectura.lecturaMongo.find({presion : data.presion});

            if(lecturas == ""){
                return response.status(200).json({message: "No hay registros con ese parámetro de búsqueda"});
            }
            return response.status(200).json(lecturas);
        } catch (error) {
            return response.status(400).json({message: "No fue procesada la operación de manera satisfactoria, intente nuevamente"});
        }

    }


    async update({request,response}){

        await this.closeConection();

        await this.mongoDBConnect();

        const data = await request.all();

        try {
            const ident = await Lectura.lecturaMongo.find({_id: Lectura.mongoose.Types.ObjectId(data._id)})

            var identificador
    
            for(let i = 0; i < ident.length; i ++){
                identificador = ident[i]
            }
    
            await Lectura.lecturaMongo.update({_id: Lectura.mongoose.Types.ObjectId(data._id)},{$set:{
                "temperatura": data.temperatura,
                "humedad" : data.humedad,
                "presion" : data.presion,
                "latitud" : data.latitud,
                "longitud" : data.longitud,
                "fecha": data.fecha,
                "identificador": identificador.identificador
                }
            })
    
            const newLectura = await Lectura.lecturaMongo.find({_id: Lectura.mongoose.Types.ObjectId(data._id)})
    
            return response.status(200).json({data: "Lectura actualizada correctamente" , "lectura" : newLectura });
        } catch (error) {
            return response.status(400).json({message: "Actualización no realizada, verifique los parámetros de entrada"});
        }


    }

    async delete({request,response}){

        await this.closeConection();

        await this.mongoDBConnect();

        const data = await request.all();

        try {
            return await Lectura.lecturaMongo.findOneAndRemove({_id: Lectura.mongoose.Types.ObjectId(data._id)});;
        } catch (error) {
            return response.status(400).json({message: "No fue procesada la operación de manera satisfactoria, intente nuevamente"});
        }
       
    }


}

module.exports = LecturaController

package com.example.listedetachesapp.Entity

class Tache(var description:String, var estTerminee:Boolean=false) {

    @JvmName("getDescription1")
    fun getDescription(): String {
        return this.description
    }

    @JvmName("setDescription1")
    fun setDescription(description: String){
        this.description=description;
    }


    @JvmName("getEstTerminee1")
    fun getEstTerminee(): Boolean {
        return this.estTerminee
    }

    @JvmName("setEstTerminee1")
    fun setEstTerminee(estTerminee: Boolean){
        this.estTerminee=estTerminee;
    }


}
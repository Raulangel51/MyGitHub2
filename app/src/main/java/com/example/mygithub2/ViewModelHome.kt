package com.example.mygithub2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import com.example.mygithub2.ApiService
import com.example.mygithub2.Git
import retrofit2.Response

class ViewModelHome : ViewModel() {
        // TODO: Implement the ViewModel

    private val _response = MutableLiveData<String>()

    var valors:String=""
    var existe = MutableLiveData<Boolean?>()

    private val _property = MutableLiveData<Git>()

    val property: LiveData<Git>
        get() = _property
//In your network successfull response


    // The external immutable LiveData for the response String
    val responsa: LiveData<String>
        get() = _response

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {

    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    public fun getGithubProperties() {
        ApiServices.retrofitService.getProperties(valors).enqueue( object: retrofit2.Callback<Git> {
            override fun onFailure(call: Call<Git>, t: Throwable) {
                _response.value = "Error " + t.message
            }

            //una vez tiene larespuesta de la pagina
            override fun onResponse(call: Call<Git>, response: Response<Git>){

                //muestra el nombre del usuario
                _response.value = "Usuario: "+response.body()?.user
                if (response.body()?.user!=null){
                    _response.value = "Usuario: "+response.body()?.user
                    _property.value=response.body()
                    existe.value=false


                }else{//de lo contrario deice que no lo encontro
                    _response.value = "No existe"
                    existe.value = true
                }
            }
        })
    }
}

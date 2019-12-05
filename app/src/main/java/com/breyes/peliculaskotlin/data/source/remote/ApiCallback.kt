package com.breyes.peliculaskotlin.data.source.remote

import com.google.gson.Gson
import com.breyes.peliculaskotlin.base.BaseApiModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class ApiCallback<M>: Observer<M> {

    abstract fun onSuccess(model: M)

    abstract fun onFailure(errorMessage: String)

    abstract fun onFinish()

    override fun onComplete() {
        onFinish()
    }

    override fun onNext(t: M) {
        onSuccess(t)
    }

    override fun onSubscribe(d: Disposable?) {

    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {
            is HttpException -> {
                val httpException = e
                //httpException.response().errorBody().string()
                val code = httpException.code()
                var msg = httpException.message()
                var baseDao: BaseApiModel<M>? = null
                try {
                    val body = httpException.response().errorBody()
                    baseDao = Gson().fromJson<BaseApiModel<M>>(body!!.string(), BaseApiModel::class.java)
                } catch (exception: Exception) {
                    onFailure(exception.message!!)
                }

                if (code == 504) {
                    msg = "Respuesta de error"
                }
                if (code == 502 || code == 404) {
                    msg = "Error de conexión"
                }
                if (baseDao != null) {
//                    msg = baseDao.message
                }
                onFailure(msg)

            }
            is UnknownHostException -> onFailure("Se produjo un error el conectarse al servidor")
            is SocketTimeoutException -> onFailure("Se produjo un error el conectarse al servidor")
            else -> onFailure(e.message!!)
        }
        onFinish()
    }
}
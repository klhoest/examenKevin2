package kevin.util

import java.net.UnknownHostException

object ErrorFormatter {
    fun simplifyError(throwable: Throwable?): String {
        return when (throwable) {
            null -> "pas d'information sur l'erreur"
            is UnknownHostException -> "conection au serveur impossible"
            else -> throwable.localizedMessage
        }

    }
}
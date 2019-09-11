package kevin.datasource.network

import io.reactivex.Observable
import kevin.domain.model.Item
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface ItemApi {

    @GET("photos")
    fun getItemList(): Observable<List<Item>>
}
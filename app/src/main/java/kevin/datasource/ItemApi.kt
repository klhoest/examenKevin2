package kevin.datasource

import io.reactivex.Observable
import kevin.domain.model.Item


interface ItemApi {
    fun getFullItemList(): Observable<List<Item>>
}
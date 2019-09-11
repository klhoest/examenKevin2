package kevin.datasource

import io.reactivex.Observable
import kevin.domain.model.Item


interface ItemService {
    fun getFullItemList(): Observable<List<Item>>
}
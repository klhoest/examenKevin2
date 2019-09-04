package kevin.domain.model.repository

import io.reactivex.Observable
import kevin.domain.model.Item

interface ItemRepository {
    fun getItemFullList(): Observable<List<Item>>
}
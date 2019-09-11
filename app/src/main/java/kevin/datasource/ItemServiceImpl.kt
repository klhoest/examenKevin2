package kevin.datasource

import io.reactivex.Observable
import kevin.datasource.network.ItemApi
import kevin.datasource.network.RetrofitBuilder
import kevin.domain.model.Item

class ItemServiceImpl : ItemService {

    val itemApi: ItemApi = RetrofitBuilder.provideRetrofit().create(ItemApi::class.java)

    override fun getFullItemList(): Observable<List<Item>> {
        return itemApi.getItemList()
    }
}
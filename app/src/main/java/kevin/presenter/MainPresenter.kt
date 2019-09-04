package kevin.presenter

import kevin.domain.model.Item
import kevin.domain.model.repository.ItemRepository

class MainPresenter(val view: View, private val itemRepo: ItemRepository) {

    fun loadFullList() {
        view.showLoading()
        itemRepo.getItemFullList().subscribe { list ->
            view.hideLoading()
            view.refreshAdapter(list)
        }
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun refreshAdapter(list: List<Item>)
    }
}
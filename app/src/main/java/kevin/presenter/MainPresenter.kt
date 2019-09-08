package kevin.presenter

import android.util.Log
import kevin.domain.model.Item
import kevin.domain.model.repository.ItemRepository
import javax.inject.Inject

class MainPresenter @Inject constructor(private val itemRepo: ItemRepository) {

    private var view: View? = null

    fun loadFullList() {
        if (view == null) {
            print("call MainPresenter.initWith(view) first")
            return
        }
        //we have to put !! since the interpreter consider view as mutable
        view!!.showLoading()
        itemRepo.getItemFullList().subscribe { list ->
            view!!.hideLoading()
            view!!.refreshAdapter(list)
        }
    }

    fun initWith(view: View) {
        this.view = view
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun refreshAdapter(list: List<Item>)
    }
}
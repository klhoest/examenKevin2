package kevin.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import kevin.domain.model.Item
import kevin.domain.model.repository.ItemRepository
import kevin.util.ErrorFormatter
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
        itemRepo.getItemFullList()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    view!!.hideLoading()
                    view!!.refreshAdapter(list)
                }, {
                    view!!.hideLoading()
                    val simplifiedError = ErrorFormatter.simplifyError(it)
                    view!!.showError(simplifiedError)
                })
    }

    fun initWith(view: View) {
        this.view = view
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun refreshAdapter(list: List<Item>)
        fun showError(error: CharSequence?)
    }
}
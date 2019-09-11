package kevin.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.viiam.feednemo.R
import kevin.di.DaggerMainComponent
import kevin.domain.model.Item
import kevin.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import android.app.ProgressDialog

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private lateinit var presenter: MainPresenter
    private lateinit var loadingDialog: ProgressDialog
    private lateinit var errorDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component = DaggerMainComponent.create()

        presenter = component.mainPresenter
        presenter.initWith(this)

        loadingDialog = createLoadingDialog()
        errorDialog = createErrorDialog()
        initListener()
    }

    private fun initListener() {
        refresh.setOnClickListener { _ ->
            presenter.loadFullList()
        }
    }

    override fun showLoading() {
        showSpinner(true)
    }

    override fun hideLoading() {
        showSpinner(false)
    }

    override fun refreshAdapter(list: List<Item>) {
        list.let {
            recycler_view_item.layoutManager = LinearLayoutManager(this)
            val itemAdapter: ItemAdapter = ItemAdapter(it.toMutableList(), this)
            recycler_view_item.adapter = itemAdapter
            itemAdapter.updateAdapter(it.toMutableList())

            recycler_view_item.setHasFixedSize(true)
        }
    }

    override fun showError(error: CharSequence?) {
        errorDialog.setMessage(error)
        errorDialog.show()
    }

    private fun createLoadingDialog(): ProgressDialog {
        val result = ProgressDialog(this)
        result.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        result.setTitle("Chargement")
        result.setMessage("Recupération des items")
        result.isIndeterminate = true
        result.setCanceledOnTouchOutside(false)
        return result
    }

    private fun createErrorDialog(): ProgressDialog {
        val result = ProgressDialog(this)
        result.setTitle("Erreur")
        result.setCanceledOnTouchOutside(true)
        return result
    }

    private fun showSpinner(isLoading: Boolean) {
        if (isLoading)
            loadingDialog.show()
        else
            loadingDialog.hide()
    }

}

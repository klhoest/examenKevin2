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
            val beersAdapter: ItemAdapter = ItemAdapter(it.toMutableList(), this)
            recycler_view_item.adapter = beersAdapter
            beersAdapter.updateAdapter(it.toMutableList())

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

    /*private fun observerLiveDatas() {
        viewModel.beers.observe(this, Observer(::onBeersReceived))
        viewModel.isError.observe(this, Observer { onErrorReceived() })
        viewModel.areEmptyBeers.observe(this, Observer { onEmptyBeersReceived() })
        viewModel.isLoading.observe(this, Observer(::onLoadingStateReceived))
    }

    private fun onBeersReceived(beers: List<BeerUI>) {
        showBeers(beers)
    }*/

    /*private fun showBeers(beersUI: List<BeerUI>?) {
        beersUI?.let {
            recycler_view_beers.layoutManager = LinearLayoutManager(this)

            val beersAdapter = BeersAdapter(it.toMutableList(), this)
            recycler_view_beers.adapter = beersAdapter
            beersAdapter.updateAdapter(it.toMutableList())

            recycler_view_beers.setHasFixedSize(true)
        }
    }*/

    private fun onErrorReceived() {
        // do something
    }

    private fun onEmptyBeersReceived() {
        // do something
    }

    private fun onLoadingStateReceived(isLoading: Boolean) {
        showSpinner(isLoading)
    }

    private fun showSpinner(isLoading: Boolean) {
        if (isLoading)
            loadingDialog.show()
        else
            loadingDialog.hide()
    }

}

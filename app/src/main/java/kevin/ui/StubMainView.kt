package kevin.ui

import kevin.domain.model.Item
import kevin.presenter.MainPresenter

class StubMainView : MainPresenter.View {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun refreshAdapter(list: List<Item>) {
        System.out.print(list.toString())
    }

    override fun showError(error: CharSequence?) {
    }
}
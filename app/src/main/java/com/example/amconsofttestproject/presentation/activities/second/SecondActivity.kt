package com.example.amconsofttestproject.presentation.activities.second

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amconsofttestproject.R
import com.example.amconsofttestproject.di.component.ViewModelComponent
import com.example.amconsofttestproject.domain.AllUsersViewModel
import com.example.amconsofttestproject.presentation.adapter.UserAdapter
import com.example.amconsofttestproject.presentation.base.BaseActivity
import com.example.amconsofttestproject.presentation.item.IUserItemClickListener
import com.example.amconsofttestproject.usecases.repository.database.entity.UserEntity
import kotlinx.android.synthetic.main.activity_second.*

import java.util.*
import javax.inject.Inject

class SecondActivity : BaseActivity() {

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

    var viewModel: AllUsersViewModel?= null
        @Inject set

    private val itemClickListener = object : IUserItemClickListener<UserEntity> {
        override fun openDetail(entity: UserEntity) {
            openItemDetail(entity.id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        viewModel?.getListUser()
        viewModel?.getLiveData()?.observe(this, Observer { it?.let { initRecyclerView(it) } })
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    private fun initActionBar(title: String) {
        Objects.requireNonNull(supportActionBar)?.title = title
    }

    private fun initRecyclerView(users: List<UserEntity>) {
        if (users.isNotEmpty()) {
            rvUsers.visibility = View.VISIBLE
            tvMessage.visibility = View.GONE
            val manager = LinearLayoutManager(this)
            val adapter = UserAdapter(this, users, itemClickListener)
            adapter.setItemClickListener(itemClickListener)
            rvUsers.layoutManager = manager
            rvUsers.adapter = adapter
        }
        else {
            rvUsers.visibility = View.GONE
            tvMessage.visibility = View.VISIBLE
        }
    }

    private fun openItemDetail(id: Int) {
        startActivity(DetailSecondActivity.newInstance(this, id))
    }
}

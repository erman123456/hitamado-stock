package com.app.hitamado.ui.Packing

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.hitamado.R
import com.app.hitamado.ui.Packing.form.FormPackingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_packing.*
import kotlinx.android.synthetic.main.fragment_packing.fab_add
import kotlinx.android.synthetic.main.fragment_packing.pb_loading
import kotlinx.android.synthetic.main.fragment_packing.tv_loadmore_message
import java.lang.Exception


@AndroidEntryPoint
class PackingFragment : Fragment(), View.OnClickListener {

    private val viewModel :PackingViewModel by viewModels()
    private val packingAdapter=PackingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_packing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)






        fab_add.setOnClickListener(this)
        srl_data.setOnRefreshListener {
            srl_data.isRefreshing=true
            viewModel.refresh()
            srl_data.isRefreshing=false
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.fab_add->{
                val intent=Intent(context,FormPackingActivity::class.java)
                context?.startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.init()
        viewModel.getPackingBarang()?.observe(requireActivity(), Observer { result->
            packingAdapter.submitList(result)
            try {
                rv_packing.setHasFixedSize(true)
                rv_packing.layoutManager = LinearLayoutManager(requireActivity())
                rv_packing.adapter = packingAdapter
            }catch (e:Exception){

            }
        })

        viewModel.getLoadingState()?.observe(requireActivity(), Observer { result->
            if(result){
                try {
                    pb_loading.visibility=View.VISIBLE
                }catch (e:Exception){

                }
            } else {
                try {
                    pb_loading.visibility=View.GONE
                }catch (e:Exception){

                }
            }
        })

        viewModel.getMoreLoadingState()?.observe(requireActivity(), Observer { result->
            if(result){
                try {
                    tv_loadmore_message.visibility=View.VISIBLE
                }catch (e:Exception){

                }

            } else {
                try {
                    tv_loadmore_message.visibility = View.GONE
                }catch (e:Exception){

                }

            }
        })
    }

}
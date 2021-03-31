package com.app.hitamado.ui.Delivery

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
import com.app.hitamado.ui.Delivery.form.FormDeliveryActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_delivery.*
import kotlinx.android.synthetic.main.fragment_delivery.fab_add
import kotlinx.android.synthetic.main.fragment_delivery.pb_loading
import kotlinx.android.synthetic.main.fragment_delivery.tv_loadmore_message
import java.lang.Exception

@AndroidEntryPoint
class DeliveryFragment : Fragment(), View.OnClickListener {

    private val viewModel:DeliveryViewModel by viewModels()
    private val deliveryAdapter=DeliveryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery, container, false)
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

    override fun onResume() {
        super.onResume()
        viewModel.init()
        viewModel.getAntarBarang()?.value?.dataSource?.invalidate()
        deliveryAdapter.currentList?.dataSource?.invalidate()
        deliveryAdapter.notifyDataSetChanged()
        viewModel.getAntarBarang()?.observe(requireActivity(), Observer { result->
            deliveryAdapter.submitList(result)
            deliveryAdapter.notifyDataSetChanged()
            viewModel.getAntarBarang()!!.removeObservers(this)
            try {
                rv_delivery.hasFixedSize()
                rv_delivery.layoutManager = LinearLayoutManager(requireActivity())
                rv_delivery.adapter = deliveryAdapter
            }catch (e:Exception){

            }
        })
        viewModel.getLoadingState()?.observe(requireActivity(), Observer { result->
            if(result){
                try {
                    pb_loading.visibility=View.VISIBLE
                }catch (e: Exception){

                }
            } else {
                try {
                    pb_loading.visibility=View.GONE
                }catch (e: Exception){

                }
            }
        })

        viewModel.getMoreLoadingState()?.observe(requireActivity(), Observer { result->
            if(result){
                try {
                    tv_loadmore_message.visibility=View.VISIBLE
                }catch (e:Exception){}

            } else{
                try {
                    tv_loadmore_message.visibility = View.GONE
                }catch (e:Exception){}

            }
        })
    }
    override fun onClick(v: View) {
        when(v.id){
            R.id.fab_add->{
                val intent=Intent(context,FormDeliveryActivity::class.java)
                context?.startActivity(intent)
            }
        }
    }


}
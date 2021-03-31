package com.app.hitamado.ui.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.app.hitamado.R
import com.app.hitamado.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preference = requireActivity().getSharedPreferences("APP", Context.MODE_PRIVATE)
        val photo=preference.getString("photo", null)
        val nama=preference.getString("nama",null)
        val email=preference.getString("email",null)
        val phone=preference.getString("phone",null)
        if (photo!=null){
            Glide.with(requireContext())
                .load(photo)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_loading)
                )
                .into(img_account)
            tv_username_account.text = nama
            tv_email_account.text = email
            tv_no_telpon_account.text = phone
        }
        btn_logout.setOnClickListener {
            val preference = requireContext().getSharedPreferences("APP", Context.MODE_PRIVATE)
            val editor = preference.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }
}
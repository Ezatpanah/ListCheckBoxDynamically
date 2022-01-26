package com.ezatpanah.listcheckboxdynamically

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezatpanah.listcheckboxdynamically.databinding.ActivityMainBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    private var subTaskList : MutableList<SubtaskModel> = mutableListOf()
    private lateinit var subtaskAdapter: SubtaskAdapter


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        refreshRecyc()

        binding?.apply {
            tvAddSubTask.setOnClickListener {
                if (edtchekBoxTitle.text.toString().isEmpty()) {
                    Toast.makeText(this@MainActivity, "Subtask title cannot be empty", Toast.LENGTH_LONG).show()

                } else {
                    subTaskList.add(SubtaskModel(edtchekBoxTitle.text.toString(),false))
                    refreshRecyc()
                    edtchekBoxTitle.setText("")
                    Toast.makeText(this@MainActivity, "${subTaskList.size}", Toast.LENGTH_LONG).show()



                }
            }
        }
    }

    fun refreshRecyc(){
        subtaskAdapter= SubtaskAdapter(subTaskList)
        binding?.apply {
            rvSubtaskList.apply {
                layoutManager= LinearLayoutManager(this@MainActivity)
                adapter=subtaskAdapter

            }
        }

    }

    override fun onResume() {
        super.onResume()
        refreshRecyc()

    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: EvenDelete) {
        if (event.isConfirmed) {
            refreshRecyc()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}
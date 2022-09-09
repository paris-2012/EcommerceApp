package com.example.ecommerceapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerceapp.databinding.ActivitySupportChatBinding

import com.example.ecommerceapp.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*

class SupportChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySupportChatBinding
    private lateinit var chatAdapter: SupportChatAdapter
    private var items: MutableList<SupportChat> = mutableListOf()
    private var ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("SUPPORT_CHAT")
    private val supportChatSenderData1 = SupportChatSenderData(
        button1 = "Regarding last order",
        button2 = "Regarding payment/cashback",
        button3 = "Regarding order and delivery?"
    )

    private val supportChatSenderData2 = SupportChatSenderData(
        button1 = "Want to know about order details?",
        button2 = "Want to chat with customer executive",
        button3 = "Want to call with customer executive?"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupportChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchNotes()
        addMoreChat()
    }

    private fun addSupportChat() {
        val chat = SupportChat(1, getString(R.string.hi_from_chat_bot))
        addMoreChat(chat)
    }

    private fun addMoreChat() {
        binding.apply {
            buttonReceiver.setOnClickListener {
                val chat = SupportChat(2, edtMessage.text.toString())
                addMoreChat(chat)
                edtMessage.text?.clear()
            }
        }
    }

    private fun fetchNotes() {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    items.clear()
                    for (h in snapshot.children) {
                        val chat = h.getValue(SupportChat::class.java)
                        items.add(chat!!)
                    }
                    chatAdapter =
                        SupportChatAdapter(this@SupportChatActivity, items, supportChatSenderData1)
                    binding.apply {
                        recyclerViewChat.layoutManager =
                            LinearLayoutManager(this@SupportChatActivity)
                        recyclerViewChat.adapter = chatAdapter
                    }

                    if (items.size == 1){
                        addSupportChat()
                    }
                }
            }
        })
    }

    private fun addMoreChat(chat: SupportChat) {
        val noteId = ref.push().key.toString()
        ref.child(noteId).setValue(chat).addOnCompleteListener {
            fetchNotes()
        }
    }
}
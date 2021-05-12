package com.example.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.fragment.findNavController
import com.example.demo.databinding.FragmentSecondBinding
import com.example.demo.ui.toolkit.ShineDivider
import com.example.demo.ui.toolkit.ShineDividerData

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root.apply {
            findViewById<ComposeView>(R.id.compose_container).setContent {
                MaterialTheme {
                    Column(Modifier.fillMaxWidth().wrapContentHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                        Text("Hello Compose Again!", modifier = Modifier.padding(10.dp))
                        SkyDivider()
                        Text("What a lovely divider!", modifier = Modifier.padding(10.dp))
                    }
                }
            }
        }

    }
@Composable
    private fun SkyDivider() {
    AndroidView(
        modifier = Modifier.height(15.dp), // Occupy the max size in the Compose UI tree
        factory = { context ->
            ShineDivider.Builder().withData(ShineDividerData(false, true)).build(context = context)
        },
        update = { view ->
//            If we wanted to update the state or interact with the view, this logic would go here
        }
    )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
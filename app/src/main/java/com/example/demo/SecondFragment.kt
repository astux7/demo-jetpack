package com.example.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
                    Column() {
                        Text("Hello Compose Again!", textAlign = TextAlign.Center)
                        SkyDivider()
                        Text("What a lovely divider!", textAlign = TextAlign.Center)
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
            // Creates custom view
            ShineDivider.Builder().withData(ShineDividerData(false, true)).build(context = context)
        },
        update = { view ->
            // View's been inflated or state read in this block has been updated
            // Add logic here if necessary

            // As selectedItem is read here, AndroidView will recompose
            // whenever the state changes
            // Example of Compose -> View communication
//            view.coordinator.selectedItem = selectedItem.value
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
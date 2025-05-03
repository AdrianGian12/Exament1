package com.example.exament1
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.content.edit
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class cuartofragmento : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cuartofragmento, container, false)
        val btnCerrarSesion: Button = view.findViewById(R.id.btnLogout)
        val btnEditarPerfil: Button = view.findViewById(R.id.btnEditarPerfil)
        btnEditarPerfil.setOnClickListener {
            Toast.makeText(requireContext(), "Esta funciòn aún no está implementado", Toast.LENGTH_SHORT).show()
        }
        btnCerrarSesion.setOnClickListener {
            val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("MiSesion", 0)
            sharedPreferences.edit() {
                clear()  //
            }
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        return view
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            cuartofragmento().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

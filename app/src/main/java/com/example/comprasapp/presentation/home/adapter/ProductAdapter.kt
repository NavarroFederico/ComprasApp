package com.example.comprasapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.comprasapp.R
import com.example.comprasapp.databinding.ItemProductBinding
import com.example.comprasapp.domain.model.Product

class ProductAdapter(private val products: List<Product>, private val onItemClicked: (Product) -> Unit)
    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProductBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.productImage.load(product.image) {
                placeholder(R.drawable.ic_placeholder_banner)
                error(R.drawable.ic_error_image)
                crossfade(true)
            }
            binding.productTitle.text = product.title
            binding.root.setOnClickListener {
                onItemClicked(product)
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return ProductViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }
    
    override fun getItemCount(): Int = products.size
}
package com.itplus.mtfood.Remote;

import android.content.Context;
import android.widget.Toast;

import com.itplus.mtfood.Model.CartDetail;
import com.itplus.mtfood.Model.Product;

import java.util.List;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    ProductService productService = APIUtils.getFoodService();
    public void insertFood(CartDetail item) {
        List<CartDetail> list = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(item.getName())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            list.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            list.add(item);
        }
        tinyDB.putListObject("CartList", list);
        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
    }

    public List<CartDetail> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberFood(List<CartDetail> list, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        list.get(position).setNumberInCart(list.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList", list);
        changeNumberItemsListener.changed();
    }

    public void minusNumberFood(List<CartDetail> list, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (list.get(position).getNumberInCart() == 1) {
            list.remove(position);
        } else {
            list.get(position).setNumberInCart(list.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList", list);
        changeNumberItemsListener.changed();
    }

    public double getTotalFee() {
        List<CartDetail> list = getListCart();
        double fee = 0;
        for (int i = 0; i < list.size(); i++) {
            fee = fee + (list.get(i).getUnitPrice() *  list.get(i).getNumberInCart());
        }
        return fee;
    }

}

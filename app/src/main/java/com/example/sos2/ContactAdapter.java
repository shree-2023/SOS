package com.example.sos2;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import  androidx.recyclerview.widget.RecyclerView;

public class ContactAdapter  extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    Context context;
    ArrayList<Contact_Model> arrContact;
    ContactAdapter(Context context,ArrayList<Contact_Model> arrContact){
        this.context=context;
        this.arrContact=arrContact;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
       ViewHolder viewHolder=new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.txtName.setText(arrContact.get(position).getName());
    holder.txtNum.setText(arrContact.get(position).getNumber());

    holder.llRow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String contactName = arrContact.get(position).getName();
            String contactNumber = arrContact.get(position).getNumber();

            // Pass the contact information to StoreVoicemsg
            Intent intent = new Intent(context, SendVoicemsg.class);
            intent.putExtra("CONTACT_NAME", contactName);
            intent.putExtra("CONTACT_NUMBER", contactNumber);
            context.startActivity(intent);


            Dialog dialog =new Dialog(context);
            dialog.setContentView(R.layout.update_contact);
            EditText editText=dialog.findViewById(R.id.editName);
            EditText editText1=dialog.findViewById(R.id.editNum);
            Button btnadd=dialog.findViewById(R.id.btnadd);
            TextView txtTitle=dialog.findViewById(R.id.txtTitle);
            txtTitle.setText("Update Contact");
            btnadd.setText("Update");

            editText.setText((arrContact.get(position)).getName());
            editText1.setText((arrContact.get(position)).getNumber());

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name="",number="";
                    if(!editText.getText().toString().equals("")){
                        name=editText.getText().toString();
                    }
                    else {
                        Toast.makeText(context,"Please Enter the Contact Name",Toast.LENGTH_SHORT).show();
                    }
                    if(!editText1.getText().toString().equals("")){
                        number=editText1.getText().toString();
                    }
                    else{
                        Toast.makeText(context,"Please Enter the Contact Number",Toast.LENGTH_SHORT).show();
                    }
                 arrContact.set(position,new Contact_Model(name,number));
                    notifyItemChanged(position);

                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    });
holder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context)
                .setTitle("Delete Contact")
                .setMessage("Are You sure You want to delete")
                .setIcon(R.drawable.baseline_delete_24)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    arrContact.remove(position);
                    notifyItemRemoved(position);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
        return true;
    }
});
    }

    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName,txtNum;
        LinearLayout llRow;
        public ViewHolder(View itemView){
            super(itemView);
            txtName=itemView.findViewById(R.id.txtName);
            txtNum=itemView.findViewById(R.id.txtNumber);
            llRow=itemView.findViewById(R.id.llRow);
         }
    }
}

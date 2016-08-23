package mk.com.mztransportad.makedonskizheleznici.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import mk.com.mztransportad.makedonskizheleznici.Helpers.Data;
import mk.com.mztransportad.makedonskizheleznici.Helpers.Price;
import mk.com.mztransportad.makedonskizheleznici.R;

/**
 * Created by gjorgjim on 8/23/16.
 */
public class SeePricesAdapter extends BaseAdapter {

    private List<Price> priceList = null;
    private Context context;

    public SeePricesAdapter(Context context, List<Price> priceList) {
        this.priceList = new ArrayList<>();
        this.priceList = priceList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return priceList.size();
    }

    @Override
    public Object getItem(int position) {
        return priceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.price_list_view_layout, null);
        Price currPrice = priceList.get(position);
        TextView relation = (TextView)view.findViewById(R.id.relation);
        TextView oneway = (TextView)view.findViewById(R.id.onewayTextView);
        TextView twoway = (TextView)view.findViewById(R.id.twowaytextView);
        relation.setText(currPrice.getStart() + "-" + currPrice.getEnd());
        oneway.setText(currPrice.getOneway() + ",00");
        twoway.setText(currPrice.getTwoway() + ",00");
        return view;
    }
}

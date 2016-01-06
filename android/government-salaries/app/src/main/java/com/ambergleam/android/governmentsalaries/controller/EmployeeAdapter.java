package com.ambergleam.android.governmentsalaries.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;

import com.ambergleam.android.governmentsalaries.R;

import java.util.List;


import static com.ambergleam.android.governmentsalaries.controller.EmployeeRow.EmployeeRowType;

public class EmployeeAdapter extends Adapter<BaseViewHolder> implements SectionIndexer {

    private EmployeeRowCollection mEmployeeRowCollection;
    private List<EmployeeRow> mRowList;
    private int mColumns;
    private String mFilterString;

    private int mHeaderHeight;
    private int mShowRowHeight;

    public EmployeeAdapter(Context context, EmployeeRowCollection employeeRowCollection, String filterString) {
        mRowList = employeeRowCollection.getRowList();
        mColumns = employeeRowCollection.getColumns();
        mEmployeeRowCollection = employeeRowCollection;
        mFilterString = filterString;
        mHeaderHeight = context.getResources().getDimensionPixelOffset(R.dimen.fragment_broadcast_item_headerHeight);
        mShowRowHeight = context.getResources().getDimensionPixelOffset(R.dimen.view_show_tile_small_image_size);
        int spacing = context.getResources().getDimensionPixelOffset(R.dimen.padding_small);
        mHeaderHeight += spacing;
        mShowRowHeight += spacing;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        switch (EmployeeRowType.values()[viewType]) {
            default:
            case HEADER:
                View view = LayoutInflater.from(context).inflate(R.layout.list_item_header, parent, false);
                return new HeaderViewHolder(view);
            case STANDARD:
                LinearLayout showTileHolderAlphabetical = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.list_item_employee, parent, false);
                return new EmployeeViewHolder(showTileHolderAlphabetical, mColumns);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        EmployeeRow row = getItem(position);
        holder.bind(row, mFilterString);
    }

    @Override
    public int getItemCount() {
        return mRowList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getEmployeeRowType().ordinal();
    }

    private EmployeeRow getItem(int position) {
        return mRowList.get(position);
    }

    public void updateAdapter(EmployeeRowCollection showRowCollection, String filterString) {
        mRowList = showRowCollection.getRowList();
        mColumns = showRowCollection.getColumns();
        mEmployeeRowCollection = showRowCollection;
        mFilterString = filterString;
        notifyDataSetChanged();
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return 0;
    }

    @Override
    public Object[] getSections() {
        List<String> headers = mEmployeeRowCollection.getHeaders();
        return headers.toArray();
    }

    @Override
    public int getSectionForPosition(int position) {
        EmployeeRow showRow = getItem(position);
        return showRow.getHeaderPosition();
    }

    public int getFullSize() {
        int size = mEmployeeRowCollection.getHeaders().size() * mHeaderHeight;
        size += (mRowList.size() - mEmployeeRowCollection.getHeaders().size()) * mShowRowHeight;
        return size;
    }

    public int getWindowPosition(int position, float topPeekPercent) {
        EmployeeRow row = getItem(position);
        int numHeaders = row.getHeaderPosition() + 1;
        int size = numHeaders * mHeaderHeight;
        size += (position + 1 - numHeaders) * mShowRowHeight;
        int height;
        if (row.getEmployeeRowType() == EmployeeRowType.HEADER) {
            height = mHeaderHeight;
        } else {
            height = mShowRowHeight;
        }
        size -= height * topPeekPercent;
        return size;
    }

}

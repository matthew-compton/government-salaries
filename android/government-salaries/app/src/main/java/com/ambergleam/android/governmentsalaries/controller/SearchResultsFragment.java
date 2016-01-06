package com.ambergleam.android.governmentsalaries.controller;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ambergleam.android.governmentsalaries.BaseApplication;
import com.ambergleam.android.governmentsalaries.BaseFragment;
import com.ambergleam.android.governmentsalaries.R;
import com.ambergleam.android.governmentsalaries.controller.fastscroller.sectionindicator.title.SectionTitleIndicator;
import com.ambergleam.android.governmentsalaries.controller.fastscroller.vertical.VerticalRecyclerViewFastScroller;
import com.ambergleam.android.governmentsalaries.model.DataManager;
import com.ambergleam.android.governmentsalaries.model.Employee;
import com.ambergleam.android.governmentsalaries.utils.AndroidUtils;
import com.ambergleam.android.governmentsalaries.view.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


import static com.ambergleam.android.governmentsalaries.view.DividerItemDecoration.*;

public class SearchResultsFragment extends BaseFragment {

    private static final String EXTRA_FILTER_STRING = "SearchResultsFragment.FilterString";
    private static final int COLUMNS = 1;

    @Inject DataManager mDataManager;

    @Bind(R.id.fragment_search_results_recyclerview) RecyclerView mRecyclerView;
    @Bind(R.id.fragment_search_results_fast_scroller) VerticalRecyclerViewFastScroller mFastScroller;
    @Bind(R.id.fragment_search_results_fast_scroller_section_title_popup) SectionTitleIndicator mSectionTitleIndicator;
    @Bind(R.id.fragment_search_results_emptyTextView) TextView mEmptyTextView;

    private String mFilterString;
    private EmployeeAdapter mEmployeeAdapter;

    public static SearchResultsFragment newInstance() {
        return new SearchResultsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.component(getActivity()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_results, parent, false);
        ButterKnife.bind(this, view);
        setupUI();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!TextUtils.isEmpty(mFilterString)) {
            outState.putString(EXTRA_FILTER_STRING, mFilterString);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search_results, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search_results_filter);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(mQueryTextListener);
        if (!TextUtils.isEmpty(mFilterString)) {
            searchView.setIconified(false);
            searchView.setQuery(mFilterString, false);
        }
    }

    private SearchView.OnQueryTextListener mQueryTextListener = new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String queryString) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String queryString) {
            queryString = queryString.toLowerCase();
            mFilterString = queryString;
            updateAdapter();
            return false;
        }

    };

    private void setupUI() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateAdapter();
        mRecyclerView.setAdapter(mEmployeeAdapter);
        mFastScroller.setRecyclerView(mRecyclerView);
        mRecyclerView.setOnScrollListener(mFastScroller.getOnScrollListener());
        mFastScroller.setSectionIndicator(mSectionTitleIndicator);
        addDivider(R.drawable.divider_vertical_extra_small);
    }

    private void updateAdapter() {
        EmployeeRowCollection broadcastItemRowCollection = getRowCollection();
        if (mEmployeeAdapter == null) {
            mEmployeeAdapter = new EmployeeAdapter(getActivity(), broadcastItemRowCollection, mFilterString);
            return;
        }

        mEmployeeAdapter.updateAdapter(broadcastItemRowCollection, mFilterString);
        mRecyclerView.post(() -> mFastScroller.updateScroll());

        boolean shouldShowList = broadcastItemRowCollection.getRowList().size() != 0;
        changeListVisibility(shouldShowList);
        updateEmptyText(!shouldShowList);
    }

    private void changeListVisibility(boolean shouldShowList) {
        int visibility = shouldShowList ? View.VISIBLE : View.INVISIBLE;
        mRecyclerView.setVisibility(visibility);
        mFastScroller.setVisibility(visibility);
        mSectionTitleIndicator.setVisibility(visibility);
    }

    private void updateEmptyText(boolean shouldShowEmptyText) {
        int visibility = shouldShowEmptyText ? View.VISIBLE : View.INVISIBLE;
        mEmptyTextView.setVisibility(visibility);

        if (!shouldShowEmptyText) {
            return;
        }

        if (TextUtils.isEmpty(mFilterString)) {
            mEmptyTextView.setText("");
        } else {
            String emptyText = getResources().getString(R.string.fragment_by_az_noShowsAvailable, mFilterString);
            mEmptyTextView.setText(emptyText);
        }
    }

    private EmployeeRowCollection getRowCollection() {
        List<Employee> broadcastItems = mDataManager.getEmployees();
        return new EmployeeRowCollection(broadcastItems, COLUMNS);
    }

    private void addDivider(int drawableResId) {
        Resources resources = getResources();

        Drawable divider = resources.getDrawable(drawableResId);
        int topPadding = resources.getDimensionPixelOffset(R.dimen.padding_small);
        int bottomPadding = resources.getDimensionPixelOffset(R.dimen.padding_small);
        DividerItemDecoration dividerView = new DividerItemDecoration(divider, Orientation.VERTICAL, topPadding, bottomPadding);
        mRecyclerView.addItemDecoration(dividerView);
    }

    @OnClick(R.id.fragment_search_results_layout)
    public void onClickScreen() {
        AndroidUtils.hideSoftwareKeyboard(getActivity());
    }

}
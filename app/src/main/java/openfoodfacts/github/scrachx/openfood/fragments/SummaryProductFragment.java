package openfoodfacts.github.scrachx.openfood.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hkm.slider.Animations.DescriptionAnimation;
import com.hkm.slider.Indicators.PagerIndicator;
import com.hkm.slider.SliderLayout;
import com.hkm.slider.SliderTypes.AdjustableSlide;
import com.hkm.slider.SliderTypes.BaseSliderView;
import java.util.ArrayList;
import butterknife.BindView;
import openfoodfacts.github.scrachx.openfood.R;
import openfoodfacts.github.scrachx.openfood.models.State;

public class SummaryProductFragment extends BaseFragment {

    @BindView(R.id.textNameProduct) TextView nameProduct;
    @BindView(R.id.textBarcodeProduct) TextView barCodeProduct;
    @BindView(R.id.textQuantityProduct) TextView quantityProduct;
    @BindView(R.id.textPackagingProduct) TextView packagingProduct;
    @BindView(R.id.textBrandProduct) TextView brandProduct;
    @BindView(R.id.textManufacturingProduct) TextView manufacturingProduct;
    @BindView(R.id.textCityProduct) TextView cityProduct;
    @BindView(R.id.textStoreProduct) TextView storeProduct;
    @BindView(R.id.textCountryProduct) TextView countryProduct;
    @BindView(R.id.textCategoryProduct) TextView categoryProduct;
    @BindView(R.id.slider) SliderLayout sliderImages;
    @BindView(R.id.custom_indicator) PagerIndicator pagerIndicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, R.layout.fragment_summary_product);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent intent = getActivity().getIntent();
        State state = (State) intent.getExtras().getSerializable("state");

        ArrayList<String> urlsImages = new ArrayList<>();
        if (state.getProduct().getImageUrl() != null) {
            urlsImages.add(state.getProduct().getImageUrl());
        }
        if (state.getProduct().getImageIngredientsUrl() != null) {
            urlsImages.add(state.getProduct().getImageIngredientsUrl());
        }
        if (state.getProduct().getImageNutritionUrl() != null) {
            urlsImages.add(state.getProduct().getImageNutritionUrl());
        }
        ArrayList<AdjustableSlide> list = new ArrayList<>();
        for (int h = 0; h < urlsImages.size(); h++) {
            AdjustableSlide textSliderView = new AdjustableSlide(view.getContext());
            textSliderView
                    .image(urlsImages.get(h))
                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
            list.add(textSliderView);
        }
        sliderImages.loadSliderList(list);
        sliderImages.setCustomAnimation(new DescriptionAnimation());
        sliderImages.setSliderTransformDuration(1000, new LinearOutSlowInInterpolator());
        sliderImages.setCustomIndicator(pagerIndicator);
        sliderImages.setDuration(5500);
        sliderImages.startAutoCycle();

        if(state.getProduct().getProductName() != null && !state.getProduct().getProductName().trim().isEmpty()) {
            nameProduct.setText(state.getProduct().getProductName());
        } else {
            nameProduct.setVisibility(View.GONE);
        }
        if(state.getProduct().getCode() != null && !state.getProduct().getCode().trim().isEmpty()) {
            barCodeProduct.setText(Html.fromHtml("<b>" + getString(R.string.txtBarcode) + "</b>" + ' ' + state.getProduct().getCode()));
        } else {
            barCodeProduct.setVisibility(View.GONE);
        }
        if(state.getProduct().getQuantity() != null && !state.getProduct().getQuantity().trim().isEmpty()) {
            quantityProduct.setText(Html.fromHtml("<b>" + getString(R.string.txtQuantity) + "</b>" + ' ' + state.getProduct().getQuantity()));
        } else {
            quantityProduct.setVisibility(View.GONE);
        }
        if(state.getProduct().getPackaging() != null && !state.getProduct().getPackaging().trim().isEmpty()) {
            packagingProduct.setText(Html.fromHtml("<b>" + getString(R.string.txtPackaging) + "</b>" + ' ' + state.getProduct().getPackaging()));
        } else {
            packagingProduct.setVisibility(View.GONE);
        }
        if(state.getProduct().getBrands() != null && !state.getProduct().getBrands().trim().isEmpty()) {
            brandProduct.setText(Html.fromHtml("<b>" + getString(R.string.txtBrands) + "</b>" + ' ' + state.getProduct().getBrands()));
        } else {
            brandProduct.setVisibility(View.GONE);
        }
        if(state.getProduct().getManufacturingPlaces() != null && !state.getProduct().getManufacturingPlaces().trim().isEmpty()) {
            manufacturingProduct.setText(Html.fromHtml("<b>" + getString(R.string.txtManufacturing) + "</b>" + ' ' + state.getProduct().getManufacturingPlaces()));
        } else {
            manufacturingProduct.setVisibility(View.GONE);
        }
        String categ;
        if (state.getProduct().getCategories() != null && !state.getProduct().getCategories().trim().isEmpty()) {
            categ = state.getProduct().getCategories().replace(",", ", ");
            categoryProduct.setText(Html.fromHtml("<b>" + getString(R.string.txtCategories) + "</b>" + ' ' + categ));
        } else {
            categoryProduct.setVisibility(View.GONE);
        }
        if(state.getProduct().getCitiesTags() != null && !state.getProduct().getCitiesTags().toString().trim().equals("[]")) {
            cityProduct.setText(Html.fromHtml("<b>" + getString(R.string.txtCity) + "</b>" + ' ' + state.getProduct().getCitiesTags().toString().replace("[", "").replace("]", "")));
        } else {
            cityProduct.setVisibility(View.GONE);
        }
        if(state.getProduct().getStores() != null && !state.getProduct().getStores().trim().isEmpty()) {
            storeProduct.setText(Html.fromHtml("<b>" + getString(R.string.txtStores) + "</b>" + ' ' + state.getProduct().getStores()));
        } else {
            storeProduct.setVisibility(View.GONE);
        }
        if(state.getProduct().getCountries() != null && !state.getProduct().getCountries().trim().isEmpty()) {
            countryProduct.setText(Html.fromHtml("<b>" + getString(R.string.txtCountries) + "</b>" + ' ' + state.getProduct().getCountries()));
        } else {
            countryProduct.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStop() {
        sliderImages.stopAutoCycle();
        super.onStop();
    }
}

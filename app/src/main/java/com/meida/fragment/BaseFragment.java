package com.meida.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;

import com.meida.nohttp.CallServer;
import com.meida.nohttp.CustomHttpListener;
import com.meida.nohttp.CustomHttpListenermoney;
import com.meida.utils.CommonUtil;
import com.meida.utils.MD5Utils;
import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.rest.Request;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimAdapterEx;

import static com.meida.share.Params.app_token;


/**
 * 作者 亢兰兰
 * 时间 2018/5/11 0011
 * 公司  郑州软盟
 */

public class BaseFragment extends Fragment {
    /**
     * RecyclerView数据管理的LayoutManager
     */
    public LinearLayoutManager linearLayoutManager;
    public GridLayoutManager gridLayoutManager;
    public StaggeredGridLayoutManager staggeredGridLayoutManager;
    /**
     * SlimAdapter的adapter
     */
    public SlimAdapter mAdapter;
    public SlimAdapterEx mAdapterex;
    /**
     * 分页加载页数
     */
    public int pageNum = 1;
    /**
     * 是否正在上拉加载中
     */
    public boolean isLoadingMore;
    /**
     * 网络请求对象.
     */
    public Request<String> mRequest;
    public int mPosition;

    //页面跳转
    public void StartActivity(Class c) {
        Intent intent = new Intent(getActivity(), c);
        this.startActivity(intent);
    }


    //Nohttp请求
    public <T> void getRequest(CustomHttpListener<T> customHttpListener, boolean isloading) {
        String Nonc = Nonce.getNonce();
        String Timestamp = Nonce.gettimes();
        mRequest.addHeader("Ha-Timestamp", Timestamp);//XX-Timestamp、XX-Nonce、XX-Token三个参数
        mRequest.addHeader("Ha-Nonce",Nonc );
        String str=mRequest.url();
        mRequest.addHeader("Ha-Signature", MD5Utils.md5Password(str.substring(str.indexOf("api/")+4)+Timestamp + Nonc + app_token));

        mRequest.addHeader("Ha-DeviceType", "1");//设备类型1安卓2苹果
        mRequest.addHeader("Ha-AppType", "1");//客户端类型1用户端2商户端
        mRequest.addHeader("Ha-VersionCode", CommonUtil.getVersion(getActivity()));
        if (!TextUtils.isEmpty(PreferencesUtils.getString(getActivity(), "uid"))) {
            mRequest.addHeader("Uid", PreferencesUtils.getString(getActivity(), "uid"));
        }
        if (!TextUtils.isEmpty(PreferencesUtils.getString(getActivity(), "token"))) {
            mRequest.addHeader("Token", PreferencesUtils.getString(getActivity(), "token"));
        }
        CallServer.getRequestInstance().add(getActivity(), mRequest, customHttpListener, isloading);
    }

    public void call(Context context, String phone) {
        if (TextUtils.isEmpty(phone)) {
            CommonUtil.showToast(context, "无效电话号码");
            return;
        }
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));//跳转到拨号界面，同时传递电话号码
        startActivity(dialIntent);
    }

    //Nohttp请求
    public <T> void getRequest(CustomHttpListenermoney<T> customHttpListener, boolean isloading) {
        String Nonc = Nonce.getNonce();
        String Timestamp = Nonce.gettimes();
        mRequest.addHeader("Ha-Timestamp", Timestamp);//XX-Timestamp、XX-Nonce、XX-Token三个参数
        mRequest.addHeader("Ha-Nonce",Nonc );
        String str=mRequest.url();
        mRequest.addHeader("Ha-Signature", MD5Utils.md5Password(str.substring(str.indexOf("api/")+4)+Timestamp + Nonc + app_token));
        mRequest.addHeader("Ha-DeviceType", "1");//设备类型1安卓2苹果
        mRequest.addHeader("Ha-AppType", "1");//客户端类型1用户端2商户端
        mRequest.addHeader("Ha-VersionCode", CommonUtil.getVersion(getActivity()));
        if (!TextUtils.isEmpty(PreferencesUtils.getString(getActivity(), "uid"))) {
            mRequest.addHeader("Uid", PreferencesUtils.getString(getActivity(), "uid"));
        }
        if (!TextUtils.isEmpty(PreferencesUtils.getString(getActivity(), "token"))) {
            mRequest.addHeader("Token", PreferencesUtils.getString(getActivity(), "token"));
        }
        CallServer.getRequestInstance().add(getActivity(), mRequest, customHttpListener, isloading);
    }
}

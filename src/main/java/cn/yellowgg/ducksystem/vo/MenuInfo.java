package cn.yellowgg.ducksystem.vo;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

/**
 * @Description: 后台管理系统的菜单vo
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 18:45
 */
@Getter
public class MenuInfo {

    private String title;
    private String icon;
    private String href;
    private String target;
    /**
     * 权限id
     */
    private Long id;
    private List<MenuInfo> children;
    /**
     * 是否选中
     */
    private boolean checked;
    /**
     * 是否展开
     */
    private boolean spread;

    public MenuInfo(String title, String icon, String href, Long id, boolean check, boolean haveChild) {
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.target = "_self";
        this.id = id;
        this.spread = true;
        this.checked = check;
        this.children = haveChild ? Lists.newArrayList() : null;
    }

    public void addChild(MenuInfo menuInfo) {
        this.children.add(menuInfo);
    }

}

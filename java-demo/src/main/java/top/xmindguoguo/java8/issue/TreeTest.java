package top.xmindguoguo.java8.issue;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeTest {
    public static void main(String[] args) {
        findChildsortList(); // 根据根节点 找到所有子节点 ，然后排序后的list
        // 学习一下怎么根据 节点 查询 一个树

    }

    private static void findChildsortList() {
        // 模拟数据库 查询一个 4层的树数据
        List<TreeModel> treeList = getTreeList();
        // 变成树
        bulid(treeList); // 变成了树数据
        // 根据根节点id 找到 根节点 model
        Long rootId = 0L;
        TreeModel rootTreeModel = findById(treeList, rootId);
        List<TreeModel> sortList = new LinkedList<>(); // 排队后的list
        sortList.add(rootTreeModel);
        if (rootTreeModel != null) {// 开始进行插队排序
            clickAll(sortList, rootTreeModel);
        }
        // 开始遍历 重新排序数据 是否符合
        for (TreeModel treeModel : sortList) {
            System.err.println(treeModel);
        }
    }

    private static TreeModel findById(List<TreeModel> treeList, Long rootId) {
        TreeModel rootTreeModel = null;
        for (TreeModel treeModel : treeList) {
            if (treeModel.getPId().equals(rootId)) {
                rootTreeModel = treeModel;
            }
        }
        return rootTreeModel;
    }

    /**
     * 深层递归找到最底层的书
     * 
     * @Title clickAll
     * @author 于国帅
     * @date 2018年8月13日 下午10:10:38
     * @param sortList
     * @param rootTreeModel
     * @return TreeModel
     */
    public static TreeModel clickAll(List<TreeModel> sortList, TreeModel rootTreeModel) {
        if (rootTreeModel != null && rootTreeModel.getCTreeList() != null) { // 如果存在子集就插队
            for (TreeModel treeModel : rootTreeModel.getCTreeList()) {
                sortList.add(treeModel);
                while (clickAll(sortList, treeModel) != null) { // 无限循环 直到 找不到子节点
                }
            }
        }
        return null;
    }

    /**
     * 两层循环实现建树
     * 
     * @param TreeModels
     *            传入的树节点列表
     * @return
     */
    public static List<TreeModel> bulid(List<TreeModel> TreeModels) {
        for (TreeModel TreeModel : TreeModels) {
            for (TreeModel it : TreeModels) {
                if (it.getPId() == TreeModel.getId()) {
                    if (TreeModel.getCTreeList() == null) {
                        TreeModel.setCTreeList(new ArrayList<TreeModel>());
                    }
                    TreeModel.getCTreeList().add(it);
                }
            }
        }
        return TreeModels;
    }

    private static List<TreeModel> getTreeList() {
        List<TreeModel> treeList = new ArrayList<>();
        /**
         * 达到这个效果
         * 
         * tree1 -- tree1.1 -- -- tree1.1.1 -- -- -- tree1.1.1.1 -- -- -- tree1.1.1.2 -- -- tree1.1.2 -- -- -- tree1.2.1.1 -- -- --
         * tree1.2.1.2 -- tree1.2 -- -- tree1.2.1 -- -- -- tree1.2.1.1 -- -- -- tree1.2.1.2
         */

        // 添加二级
        TreeModel tree11 = new TreeModel(11L, 1L);
        TreeModel tree12 = new TreeModel(12L, 1L);
        treeList.add(tree11);
        treeList.add(tree12);
        // 天添加三级
        TreeModel tree111 = new TreeModel(111L, 11L);
        TreeModel tree112 = new TreeModel(112L, 11L);
        treeList.add(tree111);
        treeList.add(tree112);

        TreeModel tree121 = new TreeModel(121L, 12L);
        TreeModel tree122 = new TreeModel(122L, 12L);
        treeList.add(tree121);
        treeList.add(tree122);
        // 添加四级
        TreeModel tree1111 = new TreeModel(1111L, 111L);
        TreeModel tree1112 = new TreeModel(1112L, 111L);
        treeList.add(tree1111);
        treeList.add(tree1112);
        // 添加顶级
        TreeModel tree1 = new TreeModel(1L, 0L); // 0证明是顶级
        treeList.add(tree1);
        return treeList;
    }

}

/**
 * 属性数据model
 *
 * @ClassName TreeModel
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年8月12日 下午10:16:28
 *
 */
@Data
class TreeModel {
    private Long id;
    private Long pId;
    private String name;
//    private String pIds;
//    private String cIds;
    private List<TreeModel> cTreeList;
//    private List<TreeModel> pTreeList;

    public TreeModel(Long id, Long pId) {
        this.id = id;
        this.pId = pId;
        this.name = "tree" + id;
    }
}


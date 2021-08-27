package concurrent;

/**
 * 分布式锁的一个优化案例，降低加锁粒度的策略
 * 比如每秒有上千订单的分布式锁高并发
 * @author Liuyongfei
 * @date 2021/8/27 13:06
 */
public class RedisLockOptimizeDemo {



    /**
     * 库存DAO
     */
    static class InventoryDAO {
        String name;

        public InventoryDAO(String name) {
            this.name = name;
        }

        /**
         * 查询库存
         * @param goodsSkuId 商品skuId
         * @return 库存
         */
        public Long getInventory(Integer goodsSkuId) {
            return 1000L;
        }

        /**
         * 更新库存
         * @param goodsSkuId 商品skuId
         * @param stockSegmentSeq 库存分段序号
         */
        public void updateInventory(Integer goodsSkuId, Integer stockSegmentSeq) {
            System.out.println("更新库存成功，goodsSkuId：【" +  goodsSkuId + "】，stockSegmentSeq：【" + stockSegmentSeq + "】");
        }
    }
}

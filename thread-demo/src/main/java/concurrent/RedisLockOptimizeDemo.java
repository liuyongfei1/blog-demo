package concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * 分布式锁的一个优化案例，降低加锁粒度的策略
 * 比如每秒有上千订单的分布式锁高并发
 * @author Liuyongfei
 * @date 2021/8/27 13:06
 */
public class RedisLockOptimizeDemo {

    public static void main(String[] args) {
        // 假如现在有一个订单，对应的 skuId: 11，purchaseCount: 56
        long goodsSkuId = 11L;
        long purchaseCount = 56L;

        // 分段查询库存
        int goodsSkuSegmentSeq = new Random().nextInt(10) + 1;

        InventoryDAO inventoryDAO = new InventoryDAO();

        // 分段加锁
        RLock lock = new RLock("stock_" + goodsSkuId + "_" + goodsSkuSegmentSeq);
        lock.lock();

        long stock = inventoryDAO.getStock(goodsSkuId, goodsSkuSegmentSeq);

        boolean foundOtherSegment = false;
        if (stock == 0L) {
            // 查找其它分段的库存
            for (int i = 1; i <= 10; i++) {
                if (goodsSkuSegmentSeq == i) {
                    continue;
                }

                lock = new RLock("stock_" + goodsSkuId + i);
                lock.lock();

                stock = inventoryDAO.getStock(goodsSkuId, i);
                if (stock > 0) {
                    foundOtherSegment = true;
                    goodsSkuSegmentSeq = i;
                    break;
                } else {
                    lock.unlock();
                }
            }

            if (!foundOtherSegment) {
                System.out.println("商品库存不足");
                return;
            }
        }

        // 该分段库存大于购买数量，则直接更新库存，并释放锁
        if (stock >= purchaseCount) {
            inventoryDAO.updateInventory(goodsSkuId, goodsSkuSegmentSeq, stock - purchaseCount);
            lock.unlock();
            return;
        }

        // 如果当前这个分段的库存小于购买数量，则需要合并其它分段库存
        long totalStock = stock;
        // 分段库存的Map
        Map<RLock, Long> otherLockMap = new HashMap<>();

        for (int i = 1; i <= 10; i++) {
            if (goodsSkuSegmentSeq == i) {
                continue;
            }

            RLock otherLock = new RLock("stock_" + goodsSkuId + "_" + i);
            otherLock.lock();

            long otherStock = inventoryDAO.getStock(goodsSkuId, i);
            if (otherStock == 0L) {
                otherLock.unlock();
                continue;
            }
            totalStock += otherStock;
            otherLockMap.put(otherLock, otherStock);

            if (totalStock >= purchaseCount) {
                break;
            }
        }

        // 如果尝试合并其它所有的分段库存仍然不够，则提示库存不足
        if (totalStock < purchaseCount) {
            System.out.println("库存不足");

            // 则释放分段锁
            for (Map.Entry<RLock, Long> otherLock : otherLockMap.entrySet()) {
                otherLock.getKey().unlock();
            }
            return;
        }

        // 走到这里，则合并分段后的库存是大于等于购买数量
        // 1.先将最初的那个分段的库存给扣掉
        long remainReducingStock = purchaseCount - stock;
        inventoryDAO.updateInventory(goodsSkuId, goodsSkuSegmentSeq, 0L);

        // 2.循环Map，并扣减其它分段库存
        for (Map.Entry<RLock, Long> otherLockEntry : otherLockMap.entrySet()) {
            if (remainReducingStock == 0L) {
                break;
            }
            RLock otherLock = otherLockEntry.getKey();
            Integer otherSegmentSeq = Integer.valueOf(otherLock.name.substring(otherLock.name.length() - 1));
            long otherStock = otherLockEntry.getValue();

            // 更新库存
            if (otherStock > remainReducingStock) {
                remainReducingStock = 0L;
                inventoryDAO.updateInventory(goodsSkuId, otherSegmentSeq, otherStock - remainReducingStock);
            } else {
                remainReducingStock -= otherStock;
                inventoryDAO.updateInventory(goodsSkuId, otherSegmentSeq, 0L);
            }
            otherLock.unlock();
        }
    }

    static class RLock {
        String name;

        public RLock(String name) {
            this.name = name;
        }

        public void lock() {
            System.out.println("加分布式锁：" + name);
        }

        public void unlock() {
            System.out.println("释放分布式锁：" + name);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof RLock)) return false;
            RLock rLock = (RLock) o;
            return Objects.equals(name, rLock.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    /**
     * 库存DAO
     */
    static class InventoryDAO {

        /**
         * 查询库存
         * @param goodsSkuId 商品skuId
         * @return 库存
         */
        public Long getStock(Long goodsSkuId, Integer stockSegmentSeq) {
            return 1000L;
        }

        /**
         * 更新库存
         * @param goodsSkuId 商品skuId
         * @param stock 剩余库存
         */
        public void updateInventory(Long goodsSkuId, Integer stockSegmentSeq, Long stock) {
            System.out.println("更新库存成功，goodsSkuId：【" +  goodsSkuId + "】，stockSegmentSeq：【" + stockSegmentSeq +
                    "】，stock：【" + stock + "】");
        }
    }
}

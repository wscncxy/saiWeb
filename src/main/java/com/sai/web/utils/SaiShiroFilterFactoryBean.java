package com.sai.web.utils;


import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

public abstract class SaiShiroFilterFactoryBean  implements InitializingBean {


//    protected abstract void doInitShiro();
//
//    protected Map<String, String> readFilterChainDefinitionMap(){
//        return new HashMap<>();
//    };
//
//    protected Map<String, String> getDefaultFilterChainDefinitionMap(){
//        return new HashMap<>();
//    };
//
//    public void afterPropertiesSet() {
//        doInitShiro();
//    }
//
//    public final void reloadShiro() {
//        synchronized (this) {
//            try {
//                Map<String, String> filterChainDefinitionMap = readFilterChainDefinitionMap();
//                AbstractShiroFilter abstractShiroFilter = (AbstractShiroFilter) this.getObject();
//                PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) abstractShiroFilter
//                        .getFilterChainResolver();
//                DefaultFilterChainManager filterManager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
//                filterManager.getFilterChains().clear();
//                this.getFilterChainDefinitionMap().clear();
//                System.out.println(this.getFilterChainDefinitionMap());
//                Map<String, String> chains = new HashMap<>();
//                if (filterChainDefinitionMap == null) {
//                    filterChainDefinitionMap = new HashMap<>();
//                }
//                filterChainDefinitionMap.putAll(getDefaultFilterChainDefinitionMap());
//                chains.putAll(filterChainDefinitionMap);
//                this.setFilterChainDefinitionMap(chains);
//                for (Map.Entry<String, String> entry : filterChainDefinitionMap.entrySet()) {
//                    String url = entry.getKey().trim().replace(" ", "");
//                    String chainDefinition = entry.getValue().trim().replace(" ", "");
//                    filterManager.createChain(url, chainDefinition);
//                }
//                System.out.println(filterManager.getFilterChains());
//                System.out.println("刷新完成");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

}

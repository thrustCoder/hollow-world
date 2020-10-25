package service;

import java.util.HashMap;
import java.util.Map;

import model.MapEntry;

/**
 * Given two configs, appConfig and defaultConfig (in JSON format),
 * return a result config containing the merged key value pairs from both the configs.
 * If there is a conflict, override with appConfig's entries.
 *
 * E.g.
 * appConfig:
 * {
 *     key1: val1
 *     key2: val2
 *     key4: {
 *         key41: val41
 *         key42: val42
 *     }
 * }
 *
 * defaultConfig:
 * {
 *     key2: val2'
 *     key3: val3'
 *     key4: {
 *         key42: val42'
 *         key43: val43'
 *     }
 * }
 *
 * resultConfig:
 * {
 *     key1: val1
 *     key2: val2
 *     key3: val3'
 *     key4: {
 *         key41: val41
 *         key42: val42
 *         key43: val43'
 *     }
 * }
 *
 * Stretch Goal:
 * Flatten the resultConfig:
 * {
 *     key1: val1
 *     key2: val2
 *     key3: val3'
 *     key4.key41: val41
 *     :: ::
 * }
 */
public class ConfigMergeAndOverrideSolution {

    // I am assuming that the JSON input has already been deserialized to Map<String, Object>

    // Time complexity: O(n + m) = O(n)
    // Space Complexity: O(n + m) = O(n)
    public Map<String, Object>  configMapMergeAndOverride(
            Map<String, Object> appConfig,
            Map<String, Object> defaultConfig) {

        // input validation
        if (appConfig == null) {
            return defaultConfig;
        } else if (defaultConfig == null) {
            return appConfig;
        } else {
            final Map<String, Object> resultConfig = new HashMap<>();
            // iterate over appConfig
            appConfig.entrySet().stream()
                    .forEach(appConfigEntry -> {
                        final String key = appConfigEntry.getKey();
                        Map.Entry<String, Object> defaultConfigEntry = new MapEntry<>(key, defaultConfig.get(key));

                        Map.Entry<String, Object> mergedEntry =  configEntryMergeAndOverride(appConfigEntry, defaultConfigEntry);
                        resultConfig.put(mergedEntry.getKey(), mergedEntry.getValue());
                    });

            // iterate over defaultConfig to add entries not present in appConfig
            defaultConfig.entrySet().stream()
                    .forEach(defaultConfigEntry -> {
                        if (appConfig.get(defaultConfigEntry.getKey()) == null) {
                            // entry exists in defaultConfig but doesn't exist in appConfig
                            // so add it to result
                            resultConfig.put(defaultConfigEntry.getKey(), defaultConfigEntry.getValue());
                        }
                    });
            return resultConfig;
        }
    }

    private Map.Entry<String, Object>  configEntryMergeAndOverride(
            Map.Entry<String, Object> appConfigEntry,
            Map.Entry<String, Object> defaultConfigEntry) {

        Object defaultConfigVal = defaultConfigEntry.getValue();

        // base case
        if (defaultConfigVal == null || defaultConfigVal instanceof String) {
            // entry is either not present in defaultConfig
            // or it's value is just a String
            // so directly override with appConfig entry 
            return appConfigEntry;

        } else {
            // entry value is a Map
            // so recurse on the entry values
            Map.Entry<String, Object> resultEntry = new MapEntry<>(
                    appConfigEntry.getKey(),
                    // recursive call on value map
                     configMapMergeAndOverride((Map) appConfigEntry.getValue(), (Map) defaultConfigVal));
            return resultEntry;
        }
    }

    // ************
    // STRETCH GOAL
    // ************

    // Create the unflattened map using the above approach, then flatten it.
    // Requires two passes.
    // Time complexity: O(2 * (n + m)) = O(n)
    // Space Complexity: O(2 * (n + m)) = O(n)
    public Map<String, String>  configMapMergeAndOverrideStretchGoal(
            Map<String, Object> appConfig,
            Map<String, Object> defaultConfig) {

        Map<String, Object> unflattenedResultMap =  configMapMergeAndOverride(appConfig, defaultConfig);

        return flattenMap(unflattenedResultMap, null);

    }

    private Map<String, String> flattenMap(
            Map<String, Object> unflattenedMap,
            String concatKey) {

        Map<String, String> flattenedResultMap = new HashMap<>();

        // input validation
        if (unflattenedMap != null) {
            // iterate over unflattenedMap
            for (Map.Entry<String, Object> entry : unflattenedMap.entrySet()) {

                Object val = entry.getValue();

                // base case
                if (val == null || val instanceof String) {
                    flattenedResultMap.put(
                            concatKey == null
                                    ? entry.getKey()
                                    : String.join(".", concatKey, entry.getKey()),
                            (String) val);
                } else {
                    // entry value is a Map
                    // so recurse on the entry values
                    return flattenMap((Map) val, concatKey);
                }
            }
        }
        return flattenedResultMap;
    }
}
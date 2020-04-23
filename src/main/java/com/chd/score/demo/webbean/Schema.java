package com.chd.score.demo.webbean;

import java.io.Serializable;
import java.util.List;

public class Schema implements Serializable {
    /**
     * success : true
     * code : 0
     * message : xxx
     * data : {"querySchema":[{"key":"blog","title":"BLOG","placeholder":"请输入网址","dataType":"varchar","showType":"normal","addonBefore":"http://","addonAfter":".me","defaultValue":"jxy"}],"dataSchema":[{"key":"id","title":"ID","dataType":"int","primary":true,"showType":"normal","showInTable":true,"disabled":false}]}
     * total : null
     */

    private boolean success = true;
    private int code = 0;
    private String message;
    private DataBean data;
    private Object total;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public static class DataBean {
        private List<QuerySchemaBean> querySchema;
        private List<DataSchemaBean> dataSchema;

        public List<QuerySchemaBean> getQuerySchema() {
            return querySchema;
        }

        public void setQuerySchema(List<QuerySchemaBean> querySchema) {
            this.querySchema = querySchema;
        }

        public List<DataSchemaBean> getDataSchema() {
            return dataSchema;
        }

        public void setDataSchema(List<DataSchemaBean> dataSchema) {
            this.dataSchema = dataSchema;
        }


        public static class QuerySchemaBean {
            /**搜索输入框
             * key : blog
             * title : BLOG
             * placeholder : 请输入网址
             * dataType : varchar
             * showType : normal
             * addonBefore : http://
             * addonAfter : .me
             * defaultValue : jxy
             */

            private String key;
            private String title;
            private String placeholder;
            private String dataType;
            private String showType;
            private String addonBefore;
            private String addonAfter;
            private String defaultValue;
            private List<OptionsBean> options;


            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPlaceholder() {
                return placeholder;
            }

            public void setPlaceholder(String placeholder) {
                this.placeholder = placeholder;
            }

            public String getDataType() {
                return dataType;
            }

            public void setDataType(String dataType) {
                this.dataType = dataType;
            }

            public String getShowType() {
                return showType;
            }

            public void setShowType(String showType) {
                this.showType = showType;
            }

            public String getAddonBefore() {
                return addonBefore;
            }

            public void setAddonBefore(String addonBefore) {
                this.addonBefore = addonBefore;
            }

            public String getAddonAfter() {
                return addonAfter;
            }

            public void setAddonAfter(String addonAfter) {
                this.addonAfter = addonAfter;
            }

            public String getDefaultValue() {
                return defaultValue;
            }

            public void setDefaultValue(String defaultValue) {
                this.defaultValue = defaultValue;
            }

            public List<OptionsBean> getOptions() {
                return options;
            }

            public void setOptions(List<OptionsBean> options) {
                this.options = options;
            }


        }

        public static class DataSchemaBean {
            /**
             * key : id
             * title : ID
             * dataType : int
             * primary : true
             * showType : normal
             * showInTable : true
             * disabled : false
             */

            private String key;
            private String title;
            private String dataType;
            private boolean primary;
            private String showType;
            private boolean showInTable;
            private boolean showInForm;
            private boolean disabled;
            private List<OptionsBean> options;
            private String validator;

            public String getValidator() {
                return validator;
            }

            public void setValidator(String validator) {
                this.validator = validator;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDataType() {
                return dataType;
            }

            public void setDataType(String dataType) {
                this.dataType = dataType;
            }

            public boolean isPrimary() {
                return primary;
            }

            public void setPrimary(boolean primary) {
                this.primary = primary;
            }

            public String getShowType() {
                return showType;
            }

            public void setShowType(String showType) {
                this.showType = showType;
            }

            public boolean isShowInTable() {
                return showInTable;
            }

            public void setShowInTable(boolean showInTable) {
                this.showInTable = showInTable;
            }

            public boolean isShowInForm() {
                return showInForm;
            }

            public void setShowInForm(boolean showInForm) {
                this.showInForm = showInForm;
            }

            public boolean isDisabled() {
                return disabled;
            }

            public void setDisabled(boolean disabled) {
                this.disabled = disabled;
            }

            public List<OptionsBean> getOptions() {
                return options;
            }

            public void setOptions(List<OptionsBean> options) {
                this.options = options;
            }

        }

        public static class OptionsBean {
            /**
             * key : type1
             * value : 类型1
             */


            private String key;
            private String value;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }



    }
    // 目前可用的showType: normal/select/radio/between/checkbox/multiSelect/cascader
    // between只能用于int/float/datetime, 会显示2个输入框, 用于范围查询
    // 目前可用的dataType: int/float/varchar/datetime






//    {
//        "success": true,
//            "code": 0,
//            "message": "xxx",
//            "data": {
//        querySchema: [
//        {
//            key: 'blog',
//                    title: 'BLOG',
//                placeholder: '请输入网址',
//                dataType: 'varchar',
//                showType: 'normal',
//                addonBefore: 'http://',
//                addonAfter: '.me',
//                defaultValue: 'jxy',
//        },
//        ],
//        dataSchema: [
//        {
//            key: 'id',
//                    title: 'ID',
//
//                dataType: 'int',
//                primary: true,
//
//                showType: 'normal',
//
//                showInTable: true,
//                disabled: false,
//
//        },
//        ],
//    },
//        "total": null
//    }

}

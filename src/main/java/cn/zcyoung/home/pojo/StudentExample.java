package cn.zcyoung.home.pojo;

import cn.zcyoung.home.util.Page;
import java.util.ArrayList;
import java.util.List;

public class StudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public StudentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPage(Page page) {
        this.page=page;
    }

    public Page getPage() {
        return page;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNoIsNull() {
            addCriterion("no is null");
            return (Criteria) this;
        }

        public Criteria andNoIsNotNull() {
            addCriterion("no is not null");
            return (Criteria) this;
        }

        public Criteria andNoEqualTo(String value) {
            addCriterion("no =", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotEqualTo(String value) {
            addCriterion("no <>", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThan(String value) {
            addCriterion("no >", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThanOrEqualTo(String value) {
            addCriterion("no >=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThan(String value) {
            addCriterion("no <", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThanOrEqualTo(String value) {
            addCriterion("no <=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLike(String value) {
            addCriterion("no like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotLike(String value) {
            addCriterion("no not like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoIn(List<String> values) {
            addCriterion("no in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotIn(List<String> values) {
            addCriterion("no not in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoBetween(String value1, String value2) {
            addCriterion("no between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotBetween(String value1, String value2) {
            addCriterion("no not between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andXjnoIsNull() {
            addCriterion("xjno is null");
            return (Criteria) this;
        }

        public Criteria andXjnoIsNotNull() {
            addCriterion("xjno is not null");
            return (Criteria) this;
        }

        public Criteria andXjnoEqualTo(String value) {
            addCriterion("xjno =", value, "xjno");
            return (Criteria) this;
        }

        public Criteria andXjnoNotEqualTo(String value) {
            addCriterion("xjno <>", value, "xjno");
            return (Criteria) this;
        }

        public Criteria andXjnoGreaterThan(String value) {
            addCriterion("xjno >", value, "xjno");
            return (Criteria) this;
        }

        public Criteria andXjnoGreaterThanOrEqualTo(String value) {
            addCriterion("xjno >=", value, "xjno");
            return (Criteria) this;
        }

        public Criteria andXjnoLessThan(String value) {
            addCriterion("xjno <", value, "xjno");
            return (Criteria) this;
        }

        public Criteria andXjnoLessThanOrEqualTo(String value) {
            addCriterion("xjno <=", value, "xjno");
            return (Criteria) this;
        }

        public Criteria andXjnoLike(String value) {
            addCriterion("xjno like", value, "xjno");
            return (Criteria) this;
        }

        public Criteria andXjnoNotLike(String value) {
            addCriterion("xjno not like", value, "xjno");
            return (Criteria) this;
        }

        public Criteria andXjnoIn(List<String> values) {
            addCriterion("xjno in", values, "xjno");
            return (Criteria) this;
        }

        public Criteria andXjnoNotIn(List<String> values) {
            addCriterion("xjno not in", values, "xjno");
            return (Criteria) this;
        }

        public Criteria andXjnoBetween(String value1, String value2) {
            addCriterion("xjno between", value1, value2, "xjno");
            return (Criteria) this;
        }

        public Criteria andXjnoNotBetween(String value1, String value2) {
            addCriterion("xjno not between", value1, value2, "xjno");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andXqIsNull() {
            addCriterion("xq is null");
            return (Criteria) this;
        }

        public Criteria andXqIsNotNull() {
            addCriterion("xq is not null");
            return (Criteria) this;
        }

        public Criteria andXqEqualTo(String value) {
            addCriterion("xq =", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotEqualTo(String value) {
            addCriterion("xq <>", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqGreaterThan(String value) {
            addCriterion("xq >", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqGreaterThanOrEqualTo(String value) {
            addCriterion("xq >=", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqLessThan(String value) {
            addCriterion("xq <", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqLessThanOrEqualTo(String value) {
            addCriterion("xq <=", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqLike(String value) {
            addCriterion("xq like", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotLike(String value) {
            addCriterion("xq not like", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqIn(List<String> values) {
            addCriterion("xq in", values, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotIn(List<String> values) {
            addCriterion("xq not in", values, "xq");
            return (Criteria) this;
        }

        public Criteria andXqBetween(String value1, String value2) {
            addCriterion("xq between", value1, value2, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotBetween(String value1, String value2) {
            addCriterion("xq not between", value1, value2, "xq");
            return (Criteria) this;
        }

        public Criteria andDepIsNull() {
            addCriterion("dep is null");
            return (Criteria) this;
        }

        public Criteria andDepIsNotNull() {
            addCriterion("dep is not null");
            return (Criteria) this;
        }

        public Criteria andDepEqualTo(String value) {
            addCriterion("dep =", value, "dep");
            return (Criteria) this;
        }

        public Criteria andDepNotEqualTo(String value) {
            addCriterion("dep <>", value, "dep");
            return (Criteria) this;
        }

        public Criteria andDepGreaterThan(String value) {
            addCriterion("dep >", value, "dep");
            return (Criteria) this;
        }

        public Criteria andDepGreaterThanOrEqualTo(String value) {
            addCriterion("dep >=", value, "dep");
            return (Criteria) this;
        }

        public Criteria andDepLessThan(String value) {
            addCriterion("dep <", value, "dep");
            return (Criteria) this;
        }

        public Criteria andDepLessThanOrEqualTo(String value) {
            addCriterion("dep <=", value, "dep");
            return (Criteria) this;
        }

        public Criteria andDepLike(String value) {
            addCriterion("dep like", value, "dep");
            return (Criteria) this;
        }

        public Criteria andDepNotLike(String value) {
            addCriterion("dep not like", value, "dep");
            return (Criteria) this;
        }

        public Criteria andDepIn(List<String> values) {
            addCriterion("dep in", values, "dep");
            return (Criteria) this;
        }

        public Criteria andDepNotIn(List<String> values) {
            addCriterion("dep not in", values, "dep");
            return (Criteria) this;
        }

        public Criteria andDepBetween(String value1, String value2) {
            addCriterion("dep between", value1, value2, "dep");
            return (Criteria) this;
        }

        public Criteria andDepNotBetween(String value1, String value2) {
            addCriterion("dep not between", value1, value2, "dep");
            return (Criteria) this;
        }

        public Criteria andGroupNoIsNull() {
            addCriterion("group_no is null");
            return (Criteria) this;
        }

        public Criteria andGroupNoIsNotNull() {
            addCriterion("group_no is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNoEqualTo(String value) {
            addCriterion("group_no =", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoNotEqualTo(String value) {
            addCriterion("group_no <>", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoGreaterThan(String value) {
            addCriterion("group_no >", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoGreaterThanOrEqualTo(String value) {
            addCriterion("group_no >=", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoLessThan(String value) {
            addCriterion("group_no <", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoLessThanOrEqualTo(String value) {
            addCriterion("group_no <=", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoLike(String value) {
            addCriterion("group_no like", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoNotLike(String value) {
            addCriterion("group_no not like", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoIn(List<String> values) {
            addCriterion("group_no in", values, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoNotIn(List<String> values) {
            addCriterion("group_no not in", values, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoBetween(String value1, String value2) {
            addCriterion("group_no between", value1, value2, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoNotBetween(String value1, String value2) {
            addCriterion("group_no not between", value1, value2, "groupNo");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
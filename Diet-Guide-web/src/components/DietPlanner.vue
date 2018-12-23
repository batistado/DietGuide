  <template>
    <div>
      <h1>Welcome to Diet Guide!</h1>
      <h2>Let's start planning</h2>
      <div class="planner">
        <transition name="slide-fade">
        <div v-show="currentPage===1">
          <h3>I can spend ${{plan.budgetRange[0]}}-{{plan.budgetRange[1]}}</h3>
          <div class="block">
            <el-slider
              v-model="plan.budgetRange"
              range
              show-stops
              :max="1000"/>
          </div>
        </div>
        </transition>
        <transition name="slide-fade">
        <div v-show="currentPage===2">
          <h3>I am a {{foodPrefTranslation[plan.foodType]}}</h3>
          <el-radio v-for="(v, k) in foodPrefTranslation" v-model="plan.foodType" :label="k" :key=k>{{v}}</el-radio>
        </div>
        </transition>
        <transition name="slide-fade">
        <div v-show="currentPage===3">
          <h3>I have specific calorie requirement</h3>
          <el-radio v-model="hasCalorieReq" :label="true">Yes</el-radio>
          <el-radio v-model="hasCalorieReq" :label="false" @change="plan.calories=0">No</el-radio>
          <div v-if="!!hasCalorieReq">
            <h3>I have a calorie requirement of {{plan.calories}} per Week</h3>
            <el-input-number v-model="plan.calories"></el-input-number>
          </div>
        </div>
        </transition>
        <transition name="slide-fade">
        <div v-show="currentPage===4">
          <h3>Are you allergic to something?</h3>
          <el-radio v-model="isAllergic" :label="true">Yes</el-radio>
          <el-radio v-model="isAllergic" :label="false" @change="plan.foodAllergies.length=0">No</el-radio>
          <div v-if="!!isAllergic">
            <h3>I am allergic to {{plan.foodAllergies.join(', ')}}</h3>
            <el-checkbox-group v-model="plan.foodAllergies" text-color="#ffffff" fill="#ffffff">
              <el-checkbox v-for="allergy in foodAllergies" :label="allergy" :key="allergy"/>
            </el-checkbox-group>
          </div>
        </div>
        </transition>
        <transition name="slide-fade">
        <div v-show="currentPage===5">
          <h3>My food preferences are {{plan.foodCategories.join(', ')}}</h3>
          <el-checkbox-group v-model="plan.foodCategories">
            <el-checkbox v-for="category in foodCategories" :label="category" :key="category"/>
          </el-checkbox-group>
        </div>
        </transition>
      </div>
      <div class="button-group">
        <el-button-group>
          <el-button type="primary" icon="el-icon-arrow-left" @click="currentPage=currentPage-1" :disabled="currentPage <= 1">Previous</el-button>
          <el-button v-if="currentPage!==maxPage" type="primary" @click="nextClicked">Next<i class="el-icon-arrow-right el-icon-right"></i></el-button>
          <el-button v-else type="primary" @click="submit">Submit<i class="el-icon-arrow-right el-icon-right"></i></el-button>
        </el-button-group>
      </div>
    </div>
  </template>
  <script>
  export default {
    name: 'diet-planner',
    data() {
      return {
        maxPage: 5,
        currentPage: 1,
        hasCalorieReq: false,
        isAllergic: false,
        foodPrefTranslation: {
          '1': 'Vegan',
          '2': 'Vegetarian',
          '3': 'Non-vegetarian',
        },
        foodAllergies: [],
        foodCategories: [],
        plan: {
          budgetRange: [0, 100],
          calories: 0,
          foodType: '1',
          foodAllergies: [],
          foodCategories: [],
        }
      }
    },
    methods: {
      nextClicked() {
        if (this.currentPage === 2){
          const loading = this.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          });
          this.axios.post(`/postFoodType?foodType=${this.plan.foodType}`, {}).then((response) => {
            this.foodAllergies = response.data.foodAllergies;
            this.foodCategories = response.data.foodCategories;
          })
          .catch(error => {
            console.log(error);
          })
          .finally(() => {
            loading.close();
          });

        }
        this.currentPage=this.currentPage+1;

      },
      submit() {
          const loading = this.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          });
          this.axios.post(`/getRecommendations`, this.plan).then((response) => {
            const responseObj = {
                response: response.data,
                from: 'dietplanner'
            }
            this.$emit('submit', responseObj);
            })
          .catch(error => {
            console.log(error);
          })
          .finally(() => {
            loading.close();
          });
      }
    }
  }
  </script>

  <style>
    div {
      text-align: center;
    }

    .slide-fade-enter-active {
      transition: all .10s ease;
    }
    .slide-fade-leave-active {
      transition: all .10s ease;
    }
    .slide-fade-enter, .slide-fade-leave-to
    /* .slide-fade-leave-active below version 2.1.8 */ {
      transform: translateX(100px);
      opacity: 0;
    }
    .planner {
      display: flex;
      justify-content: center;
    }

    .button-group {
      margin-top: 10px;
    }
  </style>
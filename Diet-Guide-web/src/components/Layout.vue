  <template>
  <div>
    <div class="header">
      <div class="header-img"/>
      <div v-if="!!canReplan">
        <el-button @click="replan">Re-plan!</el-button>
      </div>
    </div>
    <component :is="component" @submit="handleSubmit" :propsObj="propsObj"></component>
  </div>
  </template>

  <script>
  import DietPlanner from './DietPlanner.vue'
  import CustomPlans from './CustomPlans.vue'

  export default {
    name: 'layout',
    components: {
      DietPlanner,
      CustomPlans
    },
    data() {
      return {
        canReplan: false,
        componentLists: [DietPlanner, CustomPlans],
        component: DietPlanner,
        propsObj: {},
      }
    },
    methods: {
      handleSubmit(responseObj) {
        if (responseObj.from === "dietplanner"){
          this.propsObj = responseObj;
          this.component = CustomPlans;
          this.canReplan = true;
        }
      },
      replan() {
        this.propsObj = null;
        this.component = DietPlanner;
        this.canReplan = false;
      }
    }
  }
  </script>

  <style>
  .header-img {
    height: 80px;
    width: 200px;
    background-size: contain;
    background-repeat: no-repeat;
    background-color: transparent;
    background-image: url("../assets/A3.png");
  }

  .header {
    display: flex;
    justify-content: space-between;
  }
  
  body {
    color: white;
    font-family: Arial, Helvetica, sans-serif;
    background-image: url("../assets/IMG_3105.jpg");
    background-size: cover;
  }
  </style>
